package com.picpay.backend_pagamento.repositories;

import com.picpay.backend_pagamento.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdUserNot(String email, Long idUser);

    boolean existsByCpf(String cpf);
    boolean existsByCpfAndIdUserNot(String cpf, Long idUser);

    boolean existsByCnpj(String cnpj);
    boolean existsByCnpjAndIdUserNot(String cnpj, Long idUser);
}
