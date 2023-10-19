package com.agni.hotel.hotelmanagement.utills;

import java.util.List;

import lombok.Data;


@Data
public class orderResponse {

	private Integer orderId;
    private List<OrderResponseItems> items;
    private String status;
    private int totalAmount;
    
    
    
}
