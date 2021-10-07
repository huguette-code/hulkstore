package com.example.controller;

import com.example.entities.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping(value="/register")
    public String registrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping(value="/register")
    public ModelAndView saveRegister(@ModelAttribute("user") User user,  BindingResult bindingResult) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.user", "There is already a user with this email");
        }
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "error.user", "There is already a user with this username");
        }

        ModelAndView model = new ModelAndView();

        if (bindingResult.hasErrors()) {
            model.setViewName("register");
        }else{
            userService.save(user, true);
            model.setViewName("redirect:/home");
        }
        return model;
    }
}
