package com.picpay.backend_pagamento.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String name;
    private String type;

}
