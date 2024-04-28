package com.logifuture.userService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "WALLET_ID")
    private long walletId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TRANSACTION_DATE")
    private Instant transactionDate;

    @Column(name = "TRANSACTION_STATUS")
    private String transactionStatus;


}
