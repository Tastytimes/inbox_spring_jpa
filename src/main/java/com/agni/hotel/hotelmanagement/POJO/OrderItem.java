package com.agni.hotel.hotelmanagement.POJO;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;



@Data
@Entity
public class OrderItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products products;
	
	@ManyToOne
	@JoinColumn(name = "orders_id")
	private Orders orders;
	
	private double itemCost;
	
}
