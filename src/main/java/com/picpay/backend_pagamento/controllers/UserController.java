package com.picpay.backend_pagamento.controllers;

import com.picpay.backend_pagamento.entities.User;
import com.picpay.backend_pagamento.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> all() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Optional<User> one(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
     public Optional<User> updateUser(@RequestBody User updateUser, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updateUser.getName());
                    user.setEmail(updateUser.getEmail());
                    user.setPassword(updateUser.getPassword());
                    user.setCpf(updateUser.getCpf());
                    user.setCnpj(updateUser.getCnpj());
                    return userRepository.save(user);
                });
    }
}
