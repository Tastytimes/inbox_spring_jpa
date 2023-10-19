package com.agni.hotel.hotelmanagement.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.agni.hotel.hotelmanagement.POJO.CartItems;
import com.agni.hotel.hotelmanagement.POJO.OrderItem;
import com.agni.hotel.hotelmanagement.POJO.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer> {
	
//	List<OrderItem> findItemsByorder_id(Integer orderId);
	
	@Query("SELECT p FROM orders p WHERE p.id=(:orderId)")
	 Orders findById(int orderId);
	
//	@Query(value = "SELECT p FROM orders p WHERE p.user_id=(:userId)", nativeQuery= true)
//	@Query(value="SELECT p FROM orders p WHERE p.user_id=(:userId)")
//	 <List<Orders>> findByuser_id(int userId);
	
//	@Query(value="SELECT p FROM orders p wher p.user_id=(:userId)")
//	List<Orders> findOrdersByuser_Id(int userId);
	
	List<Orders> findByusers_id(Integer userId);
	
	
}
