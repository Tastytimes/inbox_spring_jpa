package com.agni.hotel.hotelmanagement.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agni.hotel.hotelmanagement.wrapper.UserWrapper;

@RequestMapping(path="/users")
public interface  UsersRest {
 
	@PostMapping(path="/signup")
	public  ResponseEntity<String> signUp(@RequestBody(required=true) Map<String, String> requestMap);
	
	@PostMapping(path = "/login")
	public ResponseEntity<String> login(@RequestBody(required=true) Map<String, String> requestMap);
	
	@GetMapping(path="/all-users")
	public ResponseEntity<List<UserWrapper>> listOfUsers();
	
	@PostMapping(path="/update-user")
	public ResponseEntity<String> updateUser(@RequestBody(required=true) Map<String, String> requestMap);
	
	@GetMapping(path="/checktoken")
	public ResponseEntity<String> checktoken();
	
	@PostMapping(path="/change-password")
	public ResponseEntity<String> changePassword(@RequestBody(required=true) Map<String, String> requestMap);
	
	
}
