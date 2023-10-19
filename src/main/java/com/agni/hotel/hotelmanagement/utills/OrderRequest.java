package com.agni.hotel.hotelmanagement.utills;

import java.util.List;

public class OrderRequest {

    private List<OrderItemRequest> items;
    
    public OrderRequest() {
    	
    }

	public OrderRequest(List<OrderItemRequest> items) {
		super();
		this.items = items;
	}

	public List<OrderItemRequest> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderRequest [items=" + items + "]";
	}
    
    
    
}
