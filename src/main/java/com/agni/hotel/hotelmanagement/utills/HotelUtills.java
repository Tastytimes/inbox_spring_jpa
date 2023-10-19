package com.agni.hotel.hotelmanagement.utills;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HotelUtills {
	
	private HotelUtills() {
		
	}
	
	public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus http){
		return new ResponseEntity<String>(responseMessage, http);
	}

}
