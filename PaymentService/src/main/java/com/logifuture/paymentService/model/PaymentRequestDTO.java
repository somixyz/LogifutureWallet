package com.logifuture.paymentService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {
    private long userId;
    private long amount;
    private String status;
    private String referenceCode;
    private PaymentMode paymentMode;
}

// public record ( long userId, long amount, String referenceNumber, PaymentMode paymentMode ){}
