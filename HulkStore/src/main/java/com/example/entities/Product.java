/**
 * 
 */
package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hcastillo
 *
 */
@Entity
@Table(name="hs_product")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4943232473978161291L;

	@Id
	@Column(name="id_product")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name",nullable = false, length = 35)
	private String name;
	
	@Column(name="brand",nullable = false, length = 35)
	private String brand;
	
	@Column(name="quantity",nullable = false)
	private int quantity;
	
	@Column(name="price",nullable = false)
	private double price;

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 *
	 * @return
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 *
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

}
