package com.agni.hotel.hotelmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.agni.hotel.hotelmanagement.POJO.OrderItem;
import com.agni.hotel.hotelmanagement.POJO.Orders;
import com.agni.hotel.hotelmanagement.utills.OrderRequest;
import com.agni.hotel.hotelmanagement.utills.orderResponse;
import com.agni.hotel.hotelmanagement.wrapper.OrderWrapper;

public interface OrdersService  {

	ResponseEntity<String> createOrders(OrderRequest orderRequest);
	
	ResponseEntity<orderResponse> getOrders(Map<String, String> requestMap);
	
	ResponseEntity<List<OrderWrapper>> getAllOrders();
	
	ResponseEntity<String> updateOrders(Map<String, String> requestMap);

	
//ResponseEntity<List<OrderItem>> orderById(Map<String, String> requestMap);
}
