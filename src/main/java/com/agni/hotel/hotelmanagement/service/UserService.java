package com.agni.hotel.hotelmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.agni.hotel.hotelmanagement.wrapper.UserWrapper;

public interface UserService {
	
	ResponseEntity<String> signUp(Map<String, String> requestMap);

	ResponseEntity<String> login(Map<String, String> requestMap);
	
	ResponseEntity<List<UserWrapper>> getAllusers();
	
	ResponseEntity<String> updateUser(Map<String, String> requestMap);
	
	ResponseEntity<String> checkToken();
	
	ResponseEntity<String> changePassword(Map<String, String> requestMap);
}
