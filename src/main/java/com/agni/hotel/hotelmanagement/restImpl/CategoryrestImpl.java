package com.agni.hotel.hotelmanagement.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.agni.hotel.hotelmanagement.POJO.Category;
import com.agni.hotel.hotelmanagement.rest.CategoryRest;
import com.agni.hotel.hotelmanagement.service.CategoryService;
import com.agni.hotel.hotelmanagement.utills.HotelUtills;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CategoryrestImpl implements CategoryRest {

	@Autowired
	CategoryService categoryService;
	
	@Override
	public ResponseEntity<String> addCategory(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			return categoryService.addCategory(requestMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategory(String filteredValue) {
		
		try {
			return categoryService.getAllCategory(filteredValue);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<List<Category>>(new ArrayList<Category>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		try {
			return categoryService.updateCategory(requestMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
