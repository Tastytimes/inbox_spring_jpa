package com.agni.hotel.hotelmanagement.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agni.hotel.hotelmanagement.POJO.OrderItem;
import com.agni.hotel.hotelmanagement.POJO.Orders;
import com.agni.hotel.hotelmanagement.utills.OrderRequest;
import com.agni.hotel.hotelmanagement.utills.orderResponse;
import com.agni.hotel.hotelmanagement.wrapper.OrderWrapper;

@RequestMapping(path="/orders")
public interface OrdersRest {

	@PostMapping(path="/create")
	public  ResponseEntity<String> createOrders(@RequestBody OrderRequest orderRequest); 
	
//	@GetMapping(path="/order")
//	public ResponseEntity<List<OrderItem>> getSingleOrder(@RequestParam Map<String, String> requestMap);
	
	@GetMapping(path="/get-order")
	public ResponseEntity<orderResponse> getOrder(@RequestParam Map<String, String> requestMap);
	
	@GetMapping(path="/get-AllOrders")
	public ResponseEntity<List<OrderWrapper>> getAllOrders();
	
	@PostMapping(path="/update")
	public  ResponseEntity<String> updateOrders(@RequestBody(required=true) Map<String, String> requestMap); 
	
}
