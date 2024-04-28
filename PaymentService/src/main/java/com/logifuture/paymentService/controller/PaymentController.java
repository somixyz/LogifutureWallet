package com.logifuture.paymentService.controller;


import com.logifuture.paymentService.model.PaymentRequestDTO;
import com.logifuture.paymentService.model.PaymentResponseDTO;
import com.logifuture.paymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequestDTO paymentRequestDTO){
        return new ResponseEntity<>(paymentService.doPayment(paymentRequestDTO),
                HttpStatus.OK);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentDetailsByUserId(@PathVariable long userId) {
        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByUserId(userId),
                HttpStatus.OK
        );
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<PaymentResponseDTO>> getAllTransactionsByUserId(@PathVariable long userId){
        List<PaymentResponseDTO> allTransactions = paymentService.getAllTransactions(userId);
        return new ResponseEntity<>(allTransactions,HttpStatus.OK);
    }


}
