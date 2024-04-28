package com.logifuture.userService.external.client;


import com.logifuture.userService.exception.UserServiceCustomException;
import com.logifuture.userService.external.request.WalletRequest;
import com.logifuture.userService.model.UserResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "WALLET-SERVICE/v1/wallets")
public interface WalletService {


    @PutMapping("/reduceFunds/{id}")
    public ResponseEntity<Void> reduceFunds(@PathVariable("id") long walletId, @RequestParam long quantity);

    @PutMapping("/increaseFunds/{id}")
    public ResponseEntity<Void> increaseFunds(HttpServletRequest request, @PathVariable("id") long walletId, @RequestParam long quantity);

    @PostMapping
    ResponseEntity<Long> addWallet(@RequestBody WalletRequest walletRequestDTO);

    @GetMapping("/{id}")
    ResponseEntity<UserResponseDTO.WalletDetails> getWalletById(@PathVariable("id") long walletId);

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWalletById(long walletId);

    default void fallback(Exception e){
        throw new UserServiceCustomException("Wallet service is not acailable", "UNAVAILABLE", 500);
    }

}
