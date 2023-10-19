package com.agni.hotel.hotelmanagement.POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String categoryName;
	
	 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//	    private List<Products> products = new ArrayList<>();
	 private Set<Products> products;
	
	
	
}
