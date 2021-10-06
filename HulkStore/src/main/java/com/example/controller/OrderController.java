package com.example.controller;

import com.example.entities.Order;
import com.example.entities.Product;
import com.example.exception.ProductOutOfStockException;
import com.example.service.OrderService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private ProductService service;

    @Autowired
    private OrderService orderService;

    @GetMapping("/buy/{id}")
    public ModelAndView buyProduct(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("cartBuy");

        Product product = service.get(id);
        mav.addObject("product", product);

        Order order = new Order();
        order.setStateOrder("PROCESSING");
        order.setProductOrder(product);

        mav.addObject("order", order);
        return mav;
    }
    @PostMapping(path = "/buyConfirm")
    public ModelAndView buyConfirmProduct(@ModelAttribute("order") Order order) {
        /*ModelAndView mav;
        Product product = service.get(order.getProductOrder().getId());
        if(product.getQuantity() >= order.getQuantity()) {
            int newQuantity = product.getQuantity() - order.getQuantity();
            product.setQuantity(newQuantity);
            service.save(product);
            orderService.save(order);
            mav = new ModelAndView("redirect:/home");
        }else {
            mav = new ModelAndView("redirect:/buy/"+order.getProductOrder().getId());
            mav.addObject("stockErrorMessage", "It product without stock.");
        }*/

        ModelAndView mav;
        try {
            orderService.processOrder(order);
            mav = new ModelAndView("redirect:/home");
        } catch (ProductOutOfStockException e) {
            mav = new ModelAndView("redirect:/buy/"+order.getProductOrder().getId());
            mav.addObject("stockErrorMessage", e.getMessage());
        }
        return mav;
    }

    @GetMapping("/orderList")
    public String orderList(Model model) {
        List<Order> orders = orderService.listAll();
        model.addAttribute("listOrder", orders);

        return "ordersList";
    }

}
