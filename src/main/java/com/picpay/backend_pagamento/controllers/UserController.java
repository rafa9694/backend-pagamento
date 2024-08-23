package com.picpay.backend_pagamento.controllers;

import com.picpay.backend_pagamento.dto.UserDTO;
import com.picpay.backend_pagamento.mappers.UserMapper;
import com.picpay.backend_pagamento.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

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
    public UserDTO newUser(@RequestBody UserDTO userDTO) {
        return userService.newUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<UserDTO> one(@PathVariable Long id) {

        UserDTO user = userService.findUserDto(id);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public  ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
     public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO updateUser) {
        updateUser = userService.updateUser(updateUser);
        return ResponseEntity.ok(updateUser);
    }
}
