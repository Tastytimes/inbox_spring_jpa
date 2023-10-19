package com.agni.hotel.hotelmanagement.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.agni.hotel.hotelmanagement.rest.UsersRest;
import com.agni.hotel.hotelmanagement.service.UserService;
import com.agni.hotel.hotelmanagement.utills.HotelUtills;
import com.agni.hotel.hotelmanagement.wrapper.UserWrapper;

@RestController
public class UsersRestImpl implements UsersRest {

	@Autowired
	UserService userService;
	
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			return userService.signUp(requestMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return HotelUtills.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			return userService.login(requestMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<List<UserWrapper>> listOfUsers() {
		try {
			return userService.getAllusers();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> updateUser(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			return userService.updateUser(requestMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> checktoken() {
		try {
			return userService.checkToken();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
			return userService.changePassword(requestMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
