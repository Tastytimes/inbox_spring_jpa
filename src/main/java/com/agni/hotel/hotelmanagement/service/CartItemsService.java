package com.agni.hotel.hotelmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.agni.hotel.hotelmanagement.POJO.CartItems;

public interface CartItemsService {

	ResponseEntity<String> addCartitems(Map<String, String> requestMap);
	
	ResponseEntity<List<CartItems>> getItems();
	
	ResponseEntity<String> updateQuantity(Map<String, String> requestMap);
}
