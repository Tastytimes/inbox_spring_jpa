package com.agni.hotel.hotelmanagement.utills;

public class OrderItemRequest {

	private String productId;
    private int quantity;
    
    public OrderItemRequest() {
    	
    }
    
	public OrderItemRequest(String productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderItemRequest [productId=" + productId + ", quantity=" + quantity + "]";
	}
    
    
}
