package com.agni.hotel.hotelmanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agni.hotel.hotelmanagement.JWT.JwtFilter;
import com.agni.hotel.hotelmanagement.POJO.CartItems;
import com.agni.hotel.hotelmanagement.POJO.Products;
import com.agni.hotel.hotelmanagement.POJO.Users;
import com.agni.hotel.hotelmanagement.dao.CartItemsDao;
import com.agni.hotel.hotelmanagement.dao.ProductDao;
import com.agni.hotel.hotelmanagement.dao.UserDao;
import com.agni.hotel.hotelmanagement.service.CartItemsService;
import com.agni.hotel.hotelmanagement.utills.HotelUtills;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartItemsServiceImpl implements CartItemsService {

	@Autowired
	CartItemsDao cartItemsDao;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductDao productDao;
	
	
	@Override
	public ResponseEntity<String> addCartitems(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			String user = jwtFilter.getCurrentUser();
			log.info("user {}", user);
			Users user1 = userDao.findByEmail(user);
			if(Objects.isNull(user1)) {
				return HotelUtills.getResponseEntity("User Not found!", HttpStatus.BAD_REQUEST);
			}else {
				if(validateProductMap(requestMap)) {
					cartItemsDao.save(mapData(requestMap, user1));
					return HotelUtills.getResponseEntity("cart items has been save successfully", HttpStatus.OK);
				}else {
					return HotelUtills.getResponseEntity("Some fields are missing", HttpStatus.BAD_REQUEST);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private boolean validateProductMap(Map<String, String> requestMap) {
		if(requestMap.containsKey("product_id") && requestMap.containsKey("quantity")) {
				return true;
		}
		return false;
	}
	
	private CartItems mapData(Map<String, String>requestMap, Users user) {
		CartItems cartitems = new CartItems();
		Optional<Products> optionalProducts = productDao.findById(Integer.parseInt(requestMap.get("product_id")));
		Products products = optionalProducts.orElse(null);
		cartitems.setProducts(products);
		cartitems.setUsers(user);
		Integer price = products.getPrice();
		Integer quantity = Integer.parseInt(requestMap.get("quantity"));
		Integer sum = quantity * price;
		cartitems.setQuantity(Integer.parseInt(requestMap.get("quantity")));
		cartitems.setTotalSum(sum);
		return cartitems;
	}

	@Override
	public ResponseEntity<List<CartItems>> getItems() {
		// TODO Auto-generated method stub
		try {
			String user = jwtFilter.getCurrentUser();
			log.info("user {}", user);
			Users user1 = userDao.findByEmail(user);
			List<CartItems> items = cartItemsDao.findByusers_id(user1.getId());
			return new ResponseEntity<List<CartItems>>(items, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<List<CartItems>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateQuantity(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			Optional<CartItems> optionalItems = cartItemsDao.findById(Integer.parseInt(requestMap.get("cartId")));
			CartItems cartItems = optionalItems.orElse(null);
			if(Objects.isNull(cartItems)) {
				return HotelUtills.getResponseEntity("Something went wrong. Please log out and relogin!", HttpStatus.BAD_REQUEST);
			}else {
				Integer quantity = Integer.parseInt(requestMap.get("quantity"));
				if(quantity == 0) {
					cartItemsDao.deleteById(Integer.parseInt(requestMap.get("cartId")));
					return HotelUtills.getResponseEntity("Cart has been updated!", HttpStatus.OK);
				}else {
					cartItems.setQuantity(quantity);
					Integer price = cartItems.getTotalSum();
					Integer totalPrice = quantity * price;
					cartItems.setTotalSum(totalPrice);
					cartItemsDao.save(cartItems);
					return HotelUtills.getResponseEntity("Cart has been updated!", HttpStatus.OK);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
