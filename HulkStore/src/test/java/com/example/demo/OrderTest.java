package com.example.demo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entities.Order;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

    @Autowired
    OrderService orderService;
    
    @Autowired
    private OrderRepository repo;
    
    @Rule
    public ExpectedException thrownException = ExpectedException.none();

    @Test
    public void testCreateOrder(){
        Order order = new Order();
        order.setClientName("TEST");
        order.setIdProducto(1L);
        order.setQuantity(2);
        orderService.save(order);
    }
    
    @Test
    public void testDeleteOrder(){
        orderService.delete(1);
    }
}
