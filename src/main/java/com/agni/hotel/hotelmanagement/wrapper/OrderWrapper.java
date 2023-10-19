package com.agni.hotel.hotelmanagement.wrapper;

import lombok.Data;

@Data
public class OrderWrapper {

	private int orderId;
	private int totalSum;
	private String status;
}
