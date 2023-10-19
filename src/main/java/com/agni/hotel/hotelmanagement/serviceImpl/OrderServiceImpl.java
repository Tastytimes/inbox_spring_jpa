package com.agni.hotel.hotelmanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agni.hotel.hotelmanagement.JWT.JwtFilter;
import com.agni.hotel.hotelmanagement.POJO.CartItems;
import com.agni.hotel.hotelmanagement.POJO.OrderItem;
import com.agni.hotel.hotelmanagement.POJO.Orders;
import com.agni.hotel.hotelmanagement.POJO.Products;
import com.agni.hotel.hotelmanagement.POJO.Users;
import com.agni.hotel.hotelmanagement.dao.CartItemsDao;
import com.agni.hotel.hotelmanagement.dao.OrderItemDao;
import com.agni.hotel.hotelmanagement.dao.OrdersDao;
import com.agni.hotel.hotelmanagement.dao.ProductDao;
import com.agni.hotel.hotelmanagement.dao.UserDao;
import com.agni.hotel.hotelmanagement.service.OrdersService;
import com.agni.hotel.hotelmanagement.utills.HotelUtills;
import com.agni.hotel.hotelmanagement.utills.OrderItemRequest;
import com.agni.hotel.hotelmanagement.utills.OrderRequest;
import com.agni.hotel.hotelmanagement.utills.OrderResponseItems;
import com.agni.hotel.hotelmanagement.utills.orderResponse;
import com.agni.hotel.hotelmanagement.wrapper.OrderWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrdersService {
	
	@Autowired
	OrdersDao orderDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Autowired
	CartItemsDao cartItemsDao;
	
	@Autowired
	OrderItemDao orderItemDao;
	
	
	
	@Override
	public ResponseEntity<String> createOrders(OrderRequest orderRequest) {
		// TODO Auto-generated method stub
	try {
		String userEmail = jwtFilter.getCurrentUser();
		Optional<Users> optionalUsers = Optional.of(userDao.findByEmail(userEmail));
		Users user = optionalUsers.orElse(null);
		if(Objects.isNull(user)) {
			return HotelUtills.getResponseEntity("User not found!", HttpStatus.BAD_REQUEST);
		}else {
			Orders orders = new Orders();
			List<CartItems> cartItems = cartItemsDao.findByusers_id(user.getId());
			List<OrderItem> orderItems = new ArrayList<>();
			int totalCost = 0;
			for(CartItems orderItemRequest: cartItems) {
				log.info("orderItemRequest {}", orderItemRequest);
				Optional<Products> optionalProducts = productDao.findById(orderItemRequest.getProducts().getId());
				Products products = optionalProducts.orElse(null);
				if(Objects.isNull(products)) {
					return HotelUtills.getResponseEntity("Cart Items not found", HttpStatus.BAD_REQUEST);
				}else {
					OrderItem orderItem = new OrderItem();
					int quantity = orderItemRequest.getQuantity();
					int price = products.getPrice();
					int total = price * quantity;
					totalCost += total;
					orderItem.setItemCost(total);
					orderItem.setQuantity(quantity);
					orderItem.setProducts(products);
					orderItem.setOrders(orders);
					orderItems.add(orderItem);
					cartItemsDao.deleteById(orderItemRequest.getId());
				}
			}
			orders.setUsers(user);
			orders.setItems(orderItems);
			orders.setStatus("created");
			orders.setTotalSum(totalCost);
			orderDao.save(orders);
			log.info("user id {}", user.getId());
			
//			cartItemsDao.deleteCartItems(user.getId());
			return HotelUtills.getResponseEntity("Order placed successfully", HttpStatus.OK);
			
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}



	@Override
	public ResponseEntity<orderResponse> getOrders(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			Integer orderId = Integer.parseInt(requestMap.get("orderId"));
			Optional<Orders> optionalorder = orderDao.findById(orderId);
			Orders order = optionalorder.orElse(null);
			if(Objects.isNull(order)) {
				return new ResponseEntity<orderResponse>(HttpStatus.BAD_REQUEST);
			}
			List<OrderResponseItems> respItemsList = new ArrayList<OrderResponseItems>();
			orderResponse resp = new orderResponse();
			resp.setStatus(order.getStatus());
			resp.setTotalAmount(order.getTotalSum());
			resp.setOrderId(order.getId());
			log.info("order {}", order);
			List<OrderItem> orderItem = orderItemDao.findItemsByorders_id(orderId);
			log.info("items {}", orderItem);
			for(OrderItem items: orderItem) {
				OrderResponseItems respItems = new OrderResponseItems();
				
				respItems.setProductId(items.getProducts().getId());
				respItems.setProductName(items.getProducts().getName());
				respItems.setQuantity(items.getQuantity());
				respItems.setItemCost(items.getItemCost());
				respItemsList.add(respItems);
			}
			resp.setItems(respItemsList);
			return new ResponseEntity<orderResponse>(resp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<orderResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
	}



	@Override
	public ResponseEntity<List<OrderWrapper>> getAllOrders() {
		// TODO Auto-generated method stub
		try {
			String userEmail = jwtFilter.getCurrentUser();
			Optional<Users> optionalUsers = Optional.of(userDao.findByEmail(userEmail));
			Users user = optionalUsers.orElse(null);
			if(Objects.isNull(user)) {
				return new ResponseEntity<List<OrderWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
			}
			List<Orders> orders = orderDao.findByusers_id(user.getId());
			log.info("orders {}", orders);
			ArrayList<OrderWrapper> wrapper = new ArrayList<>();
			for(Orders order: orders) {
				OrderWrapper ow = new OrderWrapper();
				ow.setOrderId(order.getId());
				ow.setStatus(order.getStatus());
				ow.setTotalSum(order.getTotalSum());
				wrapper.add(ow);
			}
			
	return new ResponseEntity<List<OrderWrapper>>(wrapper, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<List<OrderWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}



	@Override
	public ResponseEntity<String> updateOrders(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			if(jwtFilter.isAdmin()) {
				Orders order = orderDao.findById(Integer.parseInt(requestMap.get("orderId")));
				if(Objects.isNull(order)) {
					return HotelUtills.getResponseEntity("Order not fpund", HttpStatus.BAD_REQUEST);
				}else {
					order.setStatus(requestMap.get("status"));
					orderDao.save(order);
					return HotelUtills.getResponseEntity("Order updated successfully", HttpStatus.UNAUTHORIZED);
				}
			}else {
				return HotelUtills.getResponseEntity("user dont have access to update", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}



	
	
	

}
