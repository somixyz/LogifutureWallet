package com.logifuture.walletService.exception;

import lombok.Data;

@Data
public class WalletServiceCustomException extends RuntimeException{

    private String errorCode;

    public WalletServiceCustomException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}


