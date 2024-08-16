package com.picpay.backend_pagamento.services;

import com.picpay.backend_pagamento.dto.UserDTO;
import com.picpay.backend_pagamento.entities.User;
import com.picpay.backend_pagamento.entities.Wallet;
import com.picpay.backend_pagamento.exceptions.UserNotFoundException;
import com.picpay.backend_pagamento.mappers.UserMapper;
import com.picpay.backend_pagamento.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserMapper userMapper;

    public User newUser(User newUser) {
        Wallet wallet = new Wallet();
        Wallet newWallet = walletService.newWallet(wallet);

        newUser.setWallet(newWallet);

        return userRepository.save(newUser);
    }

    public List<UserDTO> findAllUserDto() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public UserDTO findUserDto(Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userMapper.toDto(userOptional.get());
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }
}
