package com.agni.hotel.hotelmanagement.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.agni.hotel.hotelmanagement.POJO.Products;
import com.agni.hotel.hotelmanagement.rest.ProductRest;
import com.agni.hotel.hotelmanagement.service.ProductService;
import com.agni.hotel.hotelmanagement.utills.HotelUtills;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductsRestImpl implements ProductRest {
	
	@Autowired
	ProductService productService;

	@Override
	public ResponseEntity<String> addProducts(Map<String, String> requestmap) {
		try {
			return productService.addProducts(requestmap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Products>> getProducts(Map<String, String> params) {
		// TODO Auto-generated method stub
		try {
			log.info("product restIMpl {}", params);
			return productService.getProducts(params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<List<Products>>(new ArrayList<Products>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateProducts(Map<String, String> requestmap) {
		// TODO Auto-generated method stub
		try {
			return productService.updateProducts(requestmap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteProducts(Map<String, String> params) {
		// TODO Auto-generated method stub
		try {
			return productService.deleteProducts(params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Products> getSingleProduct(Map<String, String> params) {
		// TODO Auto-generated method stub
		try {
			return productService.getSingleProduct(params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Products>( HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
