package com.agni.hotel.hotelmanagement.POJO;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.agni.hotel.hotelmanagement.wrapper.UserWrapper;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
//@NamedNativeQuery(
//	    name = "User.findUsersByRole",
//	    query = "SELECT id, email, name, contact_number, status FROM Users WHERE role = 'user'",
//	    resultClass = UserWrapper.class
//	)
@NamedQuery(name ="User.findUsersByRole", query="select new com.agni.hotel.hotelmanagement.wrapper.UserWrapper(u.id, u.email, u.name,u.contactNumber, u.Status) from Users u where u.role='user'")

@NamedQuery(name ="Users.getAllAdmin", query="select u.email from Users u where u.role='admin'")

@NamedQuery(name="Users.updateStatus", query="update Users u set u.Status=:status where u.id=:id")
public class Users implements Serializable {
	
	private static final long seerialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String contactNumber;
	
	private String email;
	
	private String password;
	
	private String Status;
	
	private String role;
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
//  private List<Products> products = new ArrayList<>();
private Set<CartItems> cartItems;
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
//  private List<Products> products = new ArrayList<>();
private Set<Orders> orders;
	
	
}
