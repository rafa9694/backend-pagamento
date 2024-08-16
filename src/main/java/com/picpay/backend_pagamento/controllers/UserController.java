package com.picpay.backend_pagamento.controllers;

import com.picpay.backend_pagamento.dto.UserDTO;
import com.picpay.backend_pagamento.entities.User;
import com.picpay.backend_pagamento.mappers.UserMapper;
import com.picpay.backend_pagamento.repositories.UserRepository;
import com.picpay.backend_pagamento.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> all() {
        List<UserDTO> users = userService.findAllUserDto();
        return ResponseEntity.ok(users);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User newUser(@RequestBody UserDTO userDTO) {
        User newUser = userMapper.toEntity(userDTO);
        return userService.newUser(newUser);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<UserDTO> one(@PathVariable Long id) {

        UserDTO user = userService.findUserDto(id);
        return ResponseEntity.ok(user);
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
