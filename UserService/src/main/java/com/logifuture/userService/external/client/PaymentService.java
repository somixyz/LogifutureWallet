package com.logifuture.userService.external.client;


import com.logifuture.userService.exception.UserServiceCustomException;
import com.logifuture.userService.external.request.PaymentRequest;
import com.logifuture.userService.model.UserResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/v1/payments")
public interface PaymentService {


    @PostMapping
    ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequestDTO);

    @GetMapping("/{userId}")
    ResponseEntity<List<UserResponseDTO.PaymentDetails>> getAllTransactionsByUserId(@PathVariable long userId);

//    @GetMapping("/user/{userId}")
//     ResponseEntity<PaymentResponseDTO> getPaymentDetailsByUserId(@PathVariable String userId);


    default void fallback(Exception e){
        throw new UserServiceCustomException("Payment service is not acailable", "UNAVAILABLE", 500);
    }


}
