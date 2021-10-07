package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entities.Product;
import com.example.exception.ProductOutOfStockException;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Order;
import com.example.repository.OrderRepository;
 
@Service
@Transactional
public class OrderService {
 
    @Autowired
    private OrderRepository repo;

    @Autowired
    private ProductRepository productRepository;

    /**
     *
      * @return
     */
    public List<Order> listAll() {
        return repo.findAll();
    }

    /**
     *
     * @param order
     */
    public void save(Order order) {
        repo.save(order);
    }

    /**
     *
     * @param id
     * @return
     */
    public Order get(long id) {
        Optional<Order> value = repo.findById(id);
        if(value.isPresent()){
            return value.get();
        }
        return null;
    }

    /**
     *
     * @param id
     */
    public void delete(long id) {
        repo.deleteById(id);
    }

    /**
     *
     * @param order
     * @throws ProductOutOfStockException
     */
    public void processOrder(Order order) throws ProductOutOfStockException {
        Optional<Product> optProduct = productRepository.findById(order.getProductOrder().getId());
        if(optProduct.isPresent()) {
            Product product = optProduct.get();
            if (product.getQuantity() < order.getQuantity())
                throw new ProductOutOfStockException();

            //update stock product.
            int newQuantity = product.getQuantity() - order.getQuantity();
            product.setQuantity(newQuantity);
            productRepository.save(product);

            repo.save(order);
        }
    }
}