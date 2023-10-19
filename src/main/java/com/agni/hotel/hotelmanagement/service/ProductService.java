package com.agni.hotel.hotelmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.agni.hotel.hotelmanagement.POJO.Products;

public interface ProductService {


	public ResponseEntity<String> addProducts(Map<String, String> requestmap);
	
	public ResponseEntity<List<Products>> getProducts(Map<String, String> requestmap);
	
	public ResponseEntity<String> updateProducts(Map<String, String> requestmap);
	
	public ResponseEntity<String> deleteProducts(Map<String, String> requestmap);
	
	public ResponseEntity<Products> getSingleProduct(Map<String, String> requestmap);
}
