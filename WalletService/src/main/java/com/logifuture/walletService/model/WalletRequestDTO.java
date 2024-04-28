package com.logifuture.walletService.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class WalletRequestDTO {

    private long walletId;
    private String walletName;
    private long balance;
}

//record WalletRequestDTO(long walletId, String walletName, long balance){}
