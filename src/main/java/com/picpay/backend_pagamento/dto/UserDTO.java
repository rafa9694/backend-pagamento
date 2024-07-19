package com.picpay.backend_pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long idUser;

    private String name;

    private String email;

    private String password;

    private String type;

    private String cpf;

    private String cnpj;

    private Long ballance;
}
