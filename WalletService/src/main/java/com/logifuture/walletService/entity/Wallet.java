package com.logifuture.walletService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WALLET_ID")
    private long walletId;

    @Column(name = "WALLET_NAME")
    private String walletName;

    @Column(name = "BALANCE")
    private long balance;

}