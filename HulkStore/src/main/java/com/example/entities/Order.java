/**
 * 
 */
package com.example.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author hcastillo
 *
 */
@Entity
@Table(name="hs_order")
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1916473030169030338L;

	@Id
	@Column(name="id_order")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_order")
	private Date date_order;
	
	@Column(name="total_order", nullable = false)
	private Double total_order;
	
	@Column(name="state_order", nullable = false)
	private String state_order;

	@OneToOne
	//@JoinTable(name="hs_user")
	@JoinColumn(name="id")
	private User user_order;

	@OneToOne
	//@JoinTable(name="hs_product")
	@JoinColumn(name="id_product")
	private Product product_order;

	@Column(name="quantity", nullable = false)
	private int quantity;
	
	@Column(name="description", nullable = false)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the date_order
	 */
	public Date getDate_order() {
		return date_order;
	}

	/**
	 * @param date_order the date_order to set
	 */
	public void setDate_order(Date date_order) {
		this.date_order = date_order;
	}

	/**
	 * @return the total_order
	 */
	public Double getTotal_order() {
		return total_order;
	}

	/**
	 * @param total_order the total_order to set
	 */
	public void setTotal_order(Double total_order) {
		this.total_order = total_order;
	}

	/**
	 * @return the user_order
	 */
	public User getUser_order() {
		return user_order;
	}

	/**
	 * @param user_order the user_order to set
	 */
	public void setUser_order(User user_order) {
		this.user_order = user_order;
	}

	/**
	 * @return the state_order
	 */
	public String getState_order() {
		return state_order;
	}

	/**
	 * @param state_order the state_order to set
	 */
	public void setState_order(String state_order) {
		this.state_order = state_order;
	}

	/**
	 * @return the product_order
	 */
	public Product getProduct_order() {
		return product_order;
	}

	/**
	 * @param product_order the product_order to set
	 */
	public void setProduct_order(Product product_order) {
		this.product_order = product_order;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}

