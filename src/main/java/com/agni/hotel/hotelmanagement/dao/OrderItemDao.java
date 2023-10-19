package com.agni.hotel.hotelmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agni.hotel.hotelmanagement.POJO.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {
	
	List<OrderItem> findItemsByorders_id(Integer orderId);

}
