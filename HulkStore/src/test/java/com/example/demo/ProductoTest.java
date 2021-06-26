package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Product;
import com.example.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductoTest {

    @Autowired
    ProductService productService;
    
    @Rule
    public ExpectedException thrownException = ExpectedException.none();

    @Test
    public void testCreateProduct(){
        Product p = newProduct("product1", 2, 2);
        productService.save(p);
        
        Product pDB = productService.get(p.getId());
        
        assertEquals(pDB.getName(), p.getName());
    }
    
    @Test
    public void testUpdateProduct(){
    	Product p = newProduct("product2", 2, 2);
    	Product p1 = newProduct("product22", 2, 2);
    	
    	List<Product> products = productService.listAll();
    	
    	assertEquals(products.size(), 2);
    	
    	p.setName(p1.getName());
    	productService.save(p1);
        
    	p = productService.get(p.getId());
    	p1 = productService.get(p1.getId());
    	
        assertEquals(p.getName(), p1.getName());
        
    }
    
    @Test
    public void testDeleteProduct(){
    	Product p = newProduct("product2", 2, 2);
    	Product p1 = newProduct("product22", 2, 2);
    	
    	int cantAfterDelete = productService.listAll().size();
    	
    	productService.delete(p.getId());
    	
    	int cantBeforeDelete = productService.listAll().size();
    	
    	assertNotEquals(cantAfterDelete, cantBeforeDelete);
        
    }
    
    private Product newProduct(String name, double price, int quantity) {
    	 Product p = new Product();
         p.setName(name);
         p.setPrice(price);
         p.setQuantity(quantity);
         productService.save(p);
         
         return p;
    }
}
