package com.logifuture.paymentService.service;

import com.logifuture.paymentService.model.PaymentRequestDTO;
import com.logifuture.paymentService.model.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {

    Long doPayment(PaymentRequestDTO paymentRequestDTO);

    PaymentResponseDTO getPaymentDetailsByUserId(long userId);

    List<PaymentResponseDTO> getAllTransactions(long userId);
}
