package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.entities.User;
import com.example.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("h@mail.com");
        user.setName("Carlos");
        //user.setNickname("User1");
        user.setPassword("password");
        //user.setRol("ADMIN");
        userService.save(user, false);
        assertNotEquals(userService.get(user.getId()), null);
    }
    @Test
    public void testDeleteUser(){
        userService.delete(1);
        assertEquals(null, userService.get(1));
    }
    
}

