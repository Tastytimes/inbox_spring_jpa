package com.agni.hotel.hotelmanagement.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agni.hotel.hotelmanagement.POJO.CartItems;

@RequestMapping(path="/cart")
public interface CartItemsRest {

	@PostMapping(path="/add")
	public  ResponseEntity<String> addCartItems(@RequestBody(required=true) Map<String, String> requestMap); 
	
	@GetMapping(path="/get")
	public ResponseEntity<List<CartItems>> getItems();
	
	@PostMapping(path="/update")
	public  ResponseEntity<String> updateQuantity(@RequestBody(required=true) Map<String, String> requestMap); 
}
