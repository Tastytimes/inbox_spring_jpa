package com.agni.hotel.hotelmanagement.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agni.hotel.hotelmanagement.JWT.JwtFilter;
import com.agni.hotel.hotelmanagement.POJO.Category;
import com.agni.hotel.hotelmanagement.POJO.Products;
import com.agni.hotel.hotelmanagement.dao.CategoryDao;
import com.agni.hotel.hotelmanagement.dao.ProductDao;
import com.agni.hotel.hotelmanagement.service.ProductService;
import com.agni.hotel.hotelmanagement.utills.HotelUtills;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Override
	public ResponseEntity<String> addProducts(Map<String, String> requestmap) {
		// TODO Auto-generated method stub
		try {
			if(jwtFilter.isAdmin()) {
				if(validateProductMap(requestmap, false)) {
					productDao.save(getProductsFromMap(requestmap, false));
					return HotelUtills.getResponseEntity("Successfully has added", HttpStatus.OK);
				}else {
					return HotelUtills.getResponseEntity("Some fields are missing", HttpStatus.BAD_REQUEST);
				}
			}else {
				return HotelUtills.getResponseEntity("Dont have access", HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
		if(requestMap.containsKey("name") && requestMap.containsKey("description") && requestMap.containsKey("price")) {
			if(requestMap.containsKey("id") && validateId) {
				return true;
			}else if(!validateId) {
				return true;
			}
		}
		return false;
	}
	
	private Products getProductsFromMap(Map<String, String>requestMap, Boolean isAdd) {
		Products products = new Products();
		Optional<Category> optionalCategory = categoryDao.findById(Integer.parseInt(requestMap.get("category")));
		Category category = optionalCategory.orElse(null);
		products.setCategory(category);
		if(isAdd) {
			products.setId(Integer.parseInt(requestMap.get("id")));
			products.setStatus(requestMap.get("status"));
		}else {
			products.setStatus("true");
		}
		
//		products.setCategory(category);
		products.setName(requestMap.get("name"));
		products.setDescription(requestMap.get("description"));
		products.setPrice(Integer.parseInt(requestMap.get("price")));
		return products;
	}

	@Override
	public ResponseEntity<List<Products>> getProducts(Map<String, String> requestmap) {
		// TODO Auto-generated method stub
		try {
			log.info("requestMap {}", requestmap);
//			List<Products> products =  productDao.findByCategory_Id(Integer.parseInt(requestmap.get("categoryId")));
//			log.info("products {}", products);
			List<Products> products =  productDao.getAll(Integer.parseInt(requestmap.get("categoryId")));
			log.info("products {}", products);
//			List<Products> products =  productDao.findAll();
			return new ResponseEntity<List<Products>>(products, HttpStatus.OK);
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
			if(jwtFilter.isAdmin()) {
				if(validateProductMap(requestmap, true)) {
					productDao.save(getProductsFromMap(requestmap, true));
					return HotelUtills.getResponseEntity("Successfully has added", HttpStatus.OK);
				}else {
					return HotelUtills.getResponseEntity("Dont have access", HttpStatus.BAD_REQUEST);
				}
			}else {
				return HotelUtills.getResponseEntity("Dont have access", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteProducts(Map<String, String> requestmap) {
		// TODO Auto-generated method stub
		try {
			if(jwtFilter.isAdmin())
			{
//				Optional<Products> product = productDao.findById(Integer.parseInt(requestmap.get("productid")));
				productDao.deleteById(Integer.parseInt(requestmap.get("productId")));
				return HotelUtills.getResponseEntity("Successfully product has been deleted", HttpStatus.OK);
			}else {
				return HotelUtills.getResponseEntity("Dont have access", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Something went wrong1", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Products> getSingleProduct(Map<String, String> requestmap) {
		// TODO Auto-generated method stub
		try {
			Optional<Products> optionalProduct = productDao.findById(Integer.parseInt(requestmap.get("productId")));
			Products products = optionalProduct.orElse(null);
			if(Objects.isNull(products)) {
				return new ResponseEntity<Products>( HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<Products>(products, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Products>( HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
