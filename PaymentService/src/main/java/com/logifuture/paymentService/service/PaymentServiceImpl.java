package com.logifuture.paymentService.service;

import com.logifuture.paymentService.entity.Transaction;
import com.logifuture.paymentService.model.PaymentMode;
import com.logifuture.paymentService.model.PaymentRequestDTO;
import com.logifuture.paymentService.model.PaymentResponseDTO;
import com.logifuture.paymentService.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Long doPayment(PaymentRequestDTO paymentRequestDTO) {
        log.info("Recording Payment Details: {}", paymentRequestDTO);

        String randomString = UUID.randomUUID().toString().replaceAll("-", "");

        Transaction transaction
                = Transaction.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequestDTO.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .userId(paymentRequestDTO.getUserId())
                .referenceCode(randomString)
                .amount(paymentRequestDTO.getAmount())
                .build();

        transactionRepository.save(transaction);

        log.info("Transaction Completed with Id: {}", transaction.getId());

        return transaction.getId();
    }

    @Override
    public PaymentResponseDTO getPaymentDetailsByUserId(long userId) {
        log.info("Payment details for the user id: {}", userId);

        Transaction transaction
                = transactionRepository.findByUserId(userId);

        return PaymentResponseDTO.builder()
                .paymentId(transaction.getId())
                .paymentMode(PaymentMode.valueOf(transaction.getPaymentMode()))
                .paymentDate(transaction.getPaymentDate())
                .userId(transaction.getUserId())
                .status(transaction.getPaymentStatus())
                .amount(transaction.getAmount())
                .build();
//        return paymentResponseDTO;
    }

    @Override
    public List<PaymentResponseDTO> getAllTransactions(long userId) {
        log.info("all transacton for user id : {}", userId);
        List<Transaction> allByUserId = transactionRepository.findAllByUserId(userId);
        List<PaymentResponseDTO> listResponse = new ArrayList<>();
        for (Transaction transaction : allByUserId) {
            PaymentResponseDTO build = PaymentResponseDTO.builder()
                    .userId(transaction.getUserId())
                    .paymentId(transaction.getId())
                    .status(transaction.getPaymentStatus())
                    .paymentDate(transaction.getPaymentDate())
                    .paymentMode(PaymentMode.valueOf(transaction.getPaymentMode()))
                    .amount(transaction.getAmount())
                    .build();
            listResponse.add(build);
        }
        return listResponse;
    }
}
