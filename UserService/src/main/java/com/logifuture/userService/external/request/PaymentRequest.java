package com.logifuture.userService.external.request;

import com.logifuture.userService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private long userId;
    private long amount;
    private String referenceCode;
    private PaymentMode paymentMode;
}
