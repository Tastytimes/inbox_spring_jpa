package com.agni.hotel.hotelmanagement.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.agni.hotel.hotelmanagement.POJO.OrderItem;
import com.agni.hotel.hotelmanagement.POJO.Orders;
import com.agni.hotel.hotelmanagement.rest.OrdersRest;
import com.agni.hotel.hotelmanagement.service.OrdersService;
import com.agni.hotel.hotelmanagement.utills.HotelUtills;
import com.agni.hotel.hotelmanagement.utills.OrderRequest;
import com.agni.hotel.hotelmanagement.utills.orderResponse;
import com.agni.hotel.hotelmanagement.wrapper.OrderWrapper;

@Controller
public class OrdersRestImpl implements OrdersRest {

	@Autowired
	OrdersService orderService;
	
	@Override
	public ResponseEntity<String> createOrders(OrderRequest orderRequest) {
		// TODO Auto-generated method stub
		try {
			return orderService.createOrders(orderRequest);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<orderResponse> getOrder(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			return orderService.getOrders(requestMap);
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
			return orderService.getAllOrders();
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
			return orderService.updateOrders(requestMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	@Override
//	public ResponseEntity<List<OrderItem>> getSingleOrder(Map<String, String> requestMap) {
//		// TODO Auto-generated method stub
//		
//		try {
//			return orderService.orderById(requestMap);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<List<OrderItem>>(new ArrayList<OrderItem>(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	

}
