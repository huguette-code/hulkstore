package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.Order;
import com.example.entities.Product;
import com.example.provider.CustomAuthenticationProvider;
import com.example.service.OrderService;
import com.example.service.ProductService;

@Controller
@RequestMapping(path = "/")
public class HulkStoreController {
	
    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    private ProductService service;
    @Autowired
    private OrderService orderService;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
	    model.addAttribute("listProducts", listProducts);
	     
	    return "productList";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showNewProductPage(Model model) {
	    Product product = new Product();
	    model.addAttribute("product", product);
	     
	    return "new_product";
	}
	
	@PostMapping(path = "/save")
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_product");
	    Product product = service.get(id);
	    mav.addObject("product", product);
	     
	    return mav;
	}
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/";       
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/loginValidate")
	public String loginValidate(HttpServletRequest req,@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
	    UsernamePasswordAuthenticationToken authReq
	      = new UsernamePasswordAuthenticationToken(username, password);
	    Authentication auth = authProvider.authenticate(authReq);
	    
	    SecurityContext sc = SecurityContextHolder.getContext();
	    sc.setAuthentication(auth);
	    HttpSession session = req.getSession(true);
	    session.setAttribute("SPRING_SECURITY_CONTEXT_KEY", sc);
		return "redirect:/";
	}
	@RequestMapping("/buy/{id}")
	public ModelAndView buyProduct(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("cartBuy");
	    Product product = service.get(id);
	    mav.addObject("product", product);
	    Order order = new Order();
	    order.setId(System.currentTimeMillis());
	    order.setIdProducto(product.getId());
	    mav.addObject("order", order);
	    return mav;
	}
	@PostMapping(path = "/buyConfirm")
	public String buyConfirmProduct(@ModelAttribute("order") Order order) {
	    //Update Stock
	    Product product = service.get(order.getIdProducto());
	    product.setQuantity(product.getQuantity()-order.getQuantity());
	    service.save(product);
	    
	    //Save Order.
	    orderService.save(order);
	    
	    return "redirect:/";
	}
	
	@RequestMapping("/orderList")
	public String orderList(Model model) {
	    List<Order> orders = orderService.listAll();
	    model.addAttribute("listOrder", orders);
	     
	    return "ordersList";
	}
	
	
}
