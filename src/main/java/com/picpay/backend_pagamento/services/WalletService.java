package com.picpay.backend_pagamento.services;

import com.picpay.backend_pagamento.entities.Wallet;
import com.picpay.backend_pagamento.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet newWallet(Wallet wallet) {
        wallet.setBallance(0l);
        return walletRepository.save(wallet);
    }
}
