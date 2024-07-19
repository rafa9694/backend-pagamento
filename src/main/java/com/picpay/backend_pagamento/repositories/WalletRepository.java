package com.picpay.backend_pagamento.repositories;

import com.picpay.backend_pagamento.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
