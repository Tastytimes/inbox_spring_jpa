package com.agni.hotel.hotelmanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agni.hotel.hotelmanagement.JWT.JwtFilter;
import com.agni.hotel.hotelmanagement.POJO.Category;
import com.agni.hotel.hotelmanagement.dao.CategoryDao;
import com.agni.hotel.hotelmanagement.service.CategoryService;
import com.agni.hotel.hotelmanagement.utills.HotelUtills;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Override
	public ResponseEntity<String> addCategory(Map<String, String> requestMap) {
		try {
			log.info("jwtFilter.isAdmin() {}", jwtFilter.isAdmin());
			if(jwtFilter.isAdmin()) {
				log.info("validateCategoryMap(requestMap, false) {}", validateCategoryMap(requestMap, false));
				if(validateCategoryMap(requestMap, false)) {
					categoryDao.save(getCategoryFromMap(requestMap, false));
					return HotelUtills.getResponseEntity("Category added successfully", HttpStatus.OK);
				}
			}else {
				return HotelUtills.getResponseEntity("Dont have enough access", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong2!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
		if(requestMap.containsKey("categoryName")) {
			if(requestMap.containsKey("id") && validateId) {
				return true;
			}else if(!validateId) {
				return true;
			}
		}
		return false;
	}
	
	private Category getCategoryFromMap(Map<String, String>requestMap, Boolean isAdd) {
		Category category = new Category();
		log.info("getCategoryFromMap( {}", requestMap);
		if(isAdd) {
		 category.setId(Integer.parseInt(requestMap.get("id")));
		}
		category.setCategoryName(requestMap.get("categoryName"));
		log.info("categor {}", category);
		return category;
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategory(String filteredValue) {
		try {
//			if(!Strings.isBlank(filteredValue) && filteredValue.equalsIgnoreCase("true")) {
//				return new ResponseEntity<List<Category>>(categoryDao.findAll(), HttpStatus.OK);
//			}
			return new ResponseEntity<List<Category>>(categoryDao.findAll(), HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<List<Category>>(new ArrayList<Category>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		try {
			log.info("jwtFilter.isAdmin() {}", jwtFilter.isAdmin());
			if(jwtFilter.isAdmin()) {
				log.info("validateCategoryMap(requestMap, false) {}", validateCategoryMap(requestMap, true));
				if(validateCategoryMap(requestMap, true)) {
					Optional cats = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
					if(!cats.isEmpty()) {
						log.info("getCategoryFromMap(requestMap, true) {}", getCategoryFromMap(requestMap, true));
						categoryDao.save(getCategoryFromMap(requestMap, true));
						return HotelUtills.getResponseEntity("Category added successfully", HttpStatus.OK);
					}else {
						return HotelUtills.getResponseEntity("Category not found", HttpStatus.NOT_FOUND);
					}
				}
			}else {
				return HotelUtills.getResponseEntity("Dont have enough access", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong2!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
