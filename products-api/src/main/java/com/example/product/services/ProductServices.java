/**
 * 
 */
package com.example.product.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dao.ProductDao;
import com.example.product.entities.Product;

/**
 * @author hcastillo
 *
 */
@RestController
@RequestMapping(value="products")
public class ProductServices {
	
	@Autowired
	private ProductDao productDao;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productDao.findAll(); 
		return ResponseEntity.ok(products);
	}
	
	@RequestMapping(value="{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
		Optional<Product> product = productDao.findById(productId);
		if(!product.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(product.get());
			
		
	}	
	
	@PostMapping
	public ResponseEntity<Product> newProduct (@RequestBody Product p){
		Product newProduct = productDao.save(p);
		return ResponseEntity.ok(newProduct);
	}
	
	@DeleteMapping(value="{productId}")
	public ResponseEntity<Void> deleteProductById (@PathVariable("productId") Long productId){
		productDao.deleteById(productId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product p){
		Optional<Product> product = productDao.findById(p.getId());
		if(product.isPresent()) {
			Product newProduct = product.get();
			newProduct.setName(p.getName());
			newProduct.setQuantity(p.getQuantity());
			productDao.save(newProduct);
			return ResponseEntity.ok(newProduct);
		}
		return ResponseEntity.notFound().build();
	}
	
	
}