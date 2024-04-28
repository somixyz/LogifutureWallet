package com.logifuture.walletService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponseDTO {

    private long walletId;
    private String walletName;
    private long balance;
}

//record WalletResponseDTO(long walletId, String walletName, long balance){}

