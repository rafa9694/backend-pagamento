package com.picpay.backend_pagamento.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Tb_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_USER_SEQ_GENERATOR")
    @SequenceGenerator(name = "TB_USER_SEQ_GENERATOR", sequenceName = "TB_USER_SEQ", allocationSize = 1)
    private Long idUser;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "TYPE", nullable = false, length = 50)
    private String type;

    @Column(name = "CPF", length = 50)
    private String cpf;

    @Column(name = "CNPJ", length = 50)
    private String cnpj;

}
