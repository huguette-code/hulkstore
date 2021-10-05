package com.example.demo;

import static org.junit.Assert.assertTrue;

import com.example.controller.ProductController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private ProductController controller;

	@Test
	public void contextLoads() throws Exception {
		assertTrue(controller == null);
	}

	@Test
	public void prueba1() throws Exception {
		String deleteResult = controller.deleteProduct(1);
		assertTrue(deleteResult == "redirect:/");
		
	}
	
	
}
