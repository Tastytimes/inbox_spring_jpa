package com.agni.hotel.hotelmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agni.hotel.hotelmanagement.POJO.Category;
import com.agni.hotel.hotelmanagement.POJO.Products;

@Repository
public interface ProductDao extends JpaRepository<Products, Integer> {
	
//	 List<Products> findByCategory(Integer categoryId);
//	List<Products> findByCategory(Category category);
//	 List<Products> findByCategory_Id(Integer categoryId);
	 
	 @Query(value="SELECT * FROM products q WHERE q.category_id=:categoryId AND q.status = 'true'", nativeQuery= true)
	 List<Products> getAll(Integer categoryId);

}
