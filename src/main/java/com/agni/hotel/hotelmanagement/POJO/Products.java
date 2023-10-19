package com.agni.hotel.hotelmanagement.POJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;


//@NamedQuery(name = "Product.getAll", query = "SELECT p FROM Products p WHERE p.status = true")


@Data
@Entity
@DynamicInsert
@DynamicUpdate

@ToString(exclude = {"cartItems"})
public class Products implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
//	
//	   @JoinTable(
//		        name = "category_product",
//		        joinColumns = @JoinColumn(name = "products_id"),
//		        inverseJoinColumns = @JoinColumn(name = "category_id")
//		    )
//	   private Optional<Category> category;
//		    private Set<Category> categoryId = new HashSet<>();
	
//	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="category_fk", nullable=false)
//	private Category category;
	
	private String description;
	
	private Integer price;
	
	private String status;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	
	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
//    private List<Products> products = new ArrayList<>();
 private Set<CartItems> cartItems;
	
	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
//  private List<Products> products = new ArrayList<>();
private Set<OrderItem> orderItem;
	
	
	
}
