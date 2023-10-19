package com.agni.hotel.hotelmanagement.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agni.hotel.hotelmanagement.POJO.Products;

@RequestMapping(path="/products")
public interface ProductRest {
	
	@PostMapping(path="/add")
	public ResponseEntity<String> addProducts(@RequestBody Map<String, String> requestmap);
	
	@GetMapping(path="/get")
	public ResponseEntity<List<Products>> getProducts(@RequestParam Map<String, String> params );
	
	@PutMapping(path="/update")
	public ResponseEntity<String> updateProducts(@RequestBody Map<String, String> requestmap);
	
	@DeleteMapping(path="/delete")
	public ResponseEntity<String> deleteProducts(@RequestParam Map<String, String> params);
	
	@GetMapping(path="/product")
	public ResponseEntity<Products> getSingleProduct(@RequestParam Map<String, String> params );
}
