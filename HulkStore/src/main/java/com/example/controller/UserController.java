package com.example.controller;

import com.example.entities.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String showNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "new_user";
    }

    @PostMapping(path = "/saveUser")
    public String saveProduct(@ModelAttribute("user") User user) {
        user.setState(1);
        userService.save(user);
        return "redirect:/userList";
    }

    @RequestMapping("/userList")
    public String userList(Model model) {
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);

        return "userList";
    }

    @GetMapping("/user/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = userService.get(id);
        mav.addObject("user", user);

        return mav;
    }
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.delete(id);
        return "redirect:/userList";
    }
}
