package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.entities.Order;
import com.example.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderTest {

    @Autowired
    OrderService orderService;

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
