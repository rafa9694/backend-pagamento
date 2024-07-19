package com.picpay.backend_pagamento.services;

import com.picpay.backend_pagamento.entities.User;
import com.picpay.backend_pagamento.entities.Wallet;
import com.picpay.backend_pagamento.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletService walletService;

    public User newUser(User newUser) {
        Wallet wallet = new Wallet();
        Wallet newWallet = walletService.newWallet(wallet);

        newUser.setWallet(newWallet);

        return userRepository.save(newUser);
    }
}
