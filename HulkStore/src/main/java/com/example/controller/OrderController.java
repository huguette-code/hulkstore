package com.example.controller;

import com.example.entities.Order;
import com.example.entities.Product;
import com.example.service.OrderService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private ProductService service;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/buy/{id}")
    public ModelAndView buyProduct(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("cartBuy");
        Product product = service.get(id);
        mav.addObject("product", product);
        Order order = new Order();
        order.setState_order("PENDING");
        order.setProduct_order(product);
        order.setId(System.currentTimeMillis());
        mav.addObject("order", order);
        return mav;
    }
    @PostMapping(path = "/buyConfirm")
    public ModelAndView buyConfirmProduct(@ModelAttribute("order") Order order) {
        ModelAndView mav;
        Product product = service.get(order.getProduct_order().getId());
        if(product.getQuantity() >= order.getQuantity()) {
            int newQuantity = product.getQuantity() - order.getQuantity();
            product.setQuantity(newQuantity);
            service.save(product);
            orderService.save(order);
            mav = new ModelAndView("redirect:/home");
        }else {
            mav = new ModelAndView("redirect:/buy/"+order.getProduct_order().getId());
            mav.addObject("stockErrorMessage", "It product without stock.");
        }

        return mav;
    }

    @RequestMapping("/orderList")
    public String orderList(Model model) {
        List<Order> orders = orderService.listAll();
        model.addAttribute("listOrder", orders);

        return "ordersList";
    }

}
