package com.agni.hotel.hotelmanagement.wrapper;

import lombok.Data;

@Data

public class UserWrapper {

	private int  id;
	private String email;
	private String name;
	private String contactNumber;
	private String status;
	

	 public UserWrapper(int id, String email, String name, String contactNumber, String status) {
	        this.id = id;
	        this.email = email;
	        this.name = name;
	        this.contactNumber = contactNumber;
	        this.status = status;
	    }
	
	public UserWrapper() {
		
	}
	
	
	
	
}
