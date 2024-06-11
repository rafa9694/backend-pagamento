package com.picpay.backend_pagamento.repositories;

import com.picpay.backend_pagamento.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
