package com.picpay.backend_pagamento.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TC_TYPE_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeUser {

    @Id
    private String type;

    @Column(name = "DS_TYPE", nullable = false, length = 100)
    private String dsType;

}
