package com.agni.hotel.hotelmanagement.POJO;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import lombok.Data;


//@NamedQuery(name="CartItems.deleteCartItems", query="delete from CartItems u where users=:userId")
@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class CartItems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products products;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;
	
	private int quantity;
	
	private int totalSum;
	
	

}
