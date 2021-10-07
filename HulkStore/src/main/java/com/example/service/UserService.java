package com.example.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.User;
import com.example.repository.UserRepository;
 
@Service
@Transactional
public class UserService {
 
    @Autowired
    private UserRepository userRepository;
    private RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }
     
    public void save(User user, boolean isRegister) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setState(1);
        if(isRegister){
            user.setRoles(Collections.singletonList(rolRepository.findByName("ROLE_USER")));
        }else{
            user.setRoles(Collections.singletonList(rolRepository.findByName("ROLE_ADMIN")));
        }
        userRepository.saveAndFlush(user);
    }
     
    public User get(long id) {
        Optional<User> value = userRepository.findById(id);
        if(value.isPresent()){
            return value.get();
        }
        return null;
    }
     
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}