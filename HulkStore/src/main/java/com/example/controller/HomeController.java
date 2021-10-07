package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.Product;
import com.example.service.ProductService;

@Controller
public class HomeController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/home")
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView("productList");

        List<Product> listProducts = service.listAll();
        model.addObject("listProducts", listProducts);
        return model;
    }
}
