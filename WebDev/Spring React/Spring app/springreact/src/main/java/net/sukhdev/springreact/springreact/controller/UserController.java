package net.sukhdev.springreact.springreact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.sukhdev.springreact.springreact.model.User;
import net.sukhdev.springreact.springreact.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List < User > getUsers() {
        return this.userRepository.findAll();
    }

    @PostMapping("users")
    public User addUser(@RequestBody User user){
        return this.userRepository.save(user);
    }
}