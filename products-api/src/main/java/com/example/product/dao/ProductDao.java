/**
 * 
 */
package com.example.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product.entities.Product;

/**
 * @author hcastillo
 *
 */

public interface ProductDao extends JpaRepository<Product, Long>{

}
