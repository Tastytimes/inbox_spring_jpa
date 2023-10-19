package com.agni.hotel.hotelmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agni.hotel.hotelmanagement.POJO.Category;
import com.agni.hotel.hotelmanagement.POJO.Users;

public interface CategoryDao extends JpaRepository<Category, Integer> {

}
