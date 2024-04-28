package com.logifuture.userService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    private String userName;
    private long walletId;
    private long quantity;
    private String walletName;
    private long balance;
    private PaymentMode paymentMode;

}

//record UserRequestDTO(String userName, long walletId, long quantity){}
