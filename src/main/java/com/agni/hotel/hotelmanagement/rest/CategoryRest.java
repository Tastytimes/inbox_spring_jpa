package com.agni.hotel.hotelmanagement.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agni.hotel.hotelmanagement.POJO.Category;

@RequestMapping(path="/category")
public interface CategoryRest {
	
	@PostMapping(path="/add")
	public  ResponseEntity<String> addCategory(@RequestBody(required=true) Map<String, String> requestMap); 
	
	@PostMapping(path="/get-category")
	public ResponseEntity<List<Category>> getAllCategory(@RequestParam(required=false) String filteredValue);
	
	@PostMapping(path="/update")
	public  ResponseEntity<String> updateCategory(@RequestBody(required=true) Map<String, String> requestMap); 

}
