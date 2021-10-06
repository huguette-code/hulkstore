package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Product;
import com.example.repository.ProductRepository;
 
@Service
@Transactional
public class ProductService {
 
    @Autowired
    private ProductRepository repo;
     
    public List<Product> listAll() {
        return repo.findAll();
    }
     
    public void save(Product product) {
        repo.save(product);
    }
     
    public Product get(long id) {
        Optional<Product> value = repo.findById(id);
        if(value.isPresent()){
            return value.get();
        }
        return null;
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}