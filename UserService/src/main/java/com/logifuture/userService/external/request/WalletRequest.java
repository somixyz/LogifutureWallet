package com.logifuture.userService.external.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletRequest {

    private long walletId;
    private String walletName;
    private long balance;
}
