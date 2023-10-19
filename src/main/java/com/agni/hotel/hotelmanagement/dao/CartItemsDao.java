package com.agni.hotel.hotelmanagement.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agni.hotel.hotelmanagement.POJO.CartItems;
import com.agni.hotel.hotelmanagement.POJO.Products;
import com.agni.hotel.hotelmanagement.POJO.Users;

import jakarta.transaction.Transactional;



@Transactional
@Repository
public interface CartItemsDao extends JpaRepository<CartItems, Integer> {
	
//	List<CartItems> findByUser(Users users);

	 List<CartItems> findByusers_id(Integer userId);
	 
//	 @Modifying
//	 @Query(value = "DELETE FROM cart_items WHERE user_id = :userId", nativeQuery = true)
//	 void deleteByuserId(int userId);
	 @Modifying
	 @Query(value = "DELETE FROM cart_items WHERE user_id = :userId", nativeQuery = true)
	 int deleteCartItems(int userId);
	 
	
}
