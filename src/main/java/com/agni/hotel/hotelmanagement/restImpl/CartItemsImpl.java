package com.agni.hotel.hotelmanagement.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.agni.hotel.hotelmanagement.POJO.CartItems;
import com.agni.hotel.hotelmanagement.rest.CartItemsRest;
import com.agni.hotel.hotelmanagement.service.CartItemsService;
import com.agni.hotel.hotelmanagement.utills.HotelUtills;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CartItemsImpl implements CartItemsRest {

	@Autowired
	CartItemsService cartItemService;
	
	@Override
	public ResponseEntity<String> addCartItems(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			return cartItemService.addCartitems(requestMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<CartItems>> getItems() {
		// TODO Auto-generated method stub
		try {
			return cartItemService.getItems();
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
			return cartItemService.updateQuantity(requestMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

}
