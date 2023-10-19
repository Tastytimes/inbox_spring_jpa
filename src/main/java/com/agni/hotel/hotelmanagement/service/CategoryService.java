package com.agni.hotel.hotelmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.agni.hotel.hotelmanagement.POJO.Category;

public interface CategoryService {
	
	ResponseEntity<String> addCategory(Map<String, String> requestMap);
	
	ResponseEntity<List<Category>> getAllCategory(String filteredValue);
	
	ResponseEntity<String> updateCategory(Map<String, String> requestMap);

}
