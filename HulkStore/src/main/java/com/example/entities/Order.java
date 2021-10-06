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
	private Date dateOrder;
	
	@Column(name="total_order", nullable = false)
	private Double totalOrder;
	
	@Column(name="state_order", nullable = false)
	private String stateOrder;

	@OneToOne
	@JoinColumn(name="id")
	private User userOrder;

	@OneToOne
	@JoinColumn(name="id_product")
	private Product productOrder;

	@Column(name="quantity", nullable = false)
	private int quantity;
	
	@Column(name="description", nullable = false)
	private String description;

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Double getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(Double totalOrder) {
		this.totalOrder = totalOrder;
	}

	public String getStateOrder() {
		return stateOrder;
	}

	public void setStateOrder(String stateOrder) {
		this.stateOrder = stateOrder;
	}

	public User getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(User userOrder) {
		this.userOrder = userOrder;
	}

	public Product getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(Product productOrder) {
		this.productOrder = productOrder;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}

