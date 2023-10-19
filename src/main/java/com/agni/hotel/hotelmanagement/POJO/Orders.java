package com.agni.hotel.hotelmanagement.POJO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

//@Data
@Entity
@DynamicInsert
@DynamicUpdate


//@ToString(exclude = "items")
public class Orders {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;
	
	
	
	private int totalSum;
	
	private String status;
	
	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<OrderItem> items = new ArrayList<>();
	
	
	public Orders() {
		
	}

	public Orders(int id, Users users, int totalSum, String status, List<OrderItem> items) {
		super();
		this.id = id;
		this.users = users;
		this.totalSum = totalSum;
		this.status = status;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public int getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(int totalSum) {
		this.totalSum = totalSum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

//	@Override
//	public String toString() {
//		return "Orders [id=" + id + ", users=" + users + ", totalSum=" + totalSum + ", status=" + status + ", items="
//				+ items + "]";
//	}
	
	
	@Override
	public String toString() {
	    return "Orders [id=" + id + ", users=" + (users != null ? users.getId() : "null") + ", totalSum=" + totalSum + ", status=" + status + "]";
	}
	
	
	
}
