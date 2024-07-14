package com.picpay.backend_pagamento.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TB_WALLET")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_WALLET_SEQ_GENERATOR")
    @SequenceGenerator(name = "TB_WALLET_SEQ_GENERATOR", sequenceName = "TB_WALLET_SEQ", allocationSize = 1)
    private Long idWallet;

    @Column(name = "BALLANCE", nullable = false, length = 12)
    private Long ballance;

}
