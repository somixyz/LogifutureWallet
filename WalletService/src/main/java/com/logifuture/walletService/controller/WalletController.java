package com.logifuture.walletService.controller;


import com.logifuture.walletService.model.WalletRequestDTO;
import com.logifuture.walletService.model.WalletResponseDTO;
import com.logifuture.walletService.service.WalletService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

@RestController
@RequestMapping("/v1/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity<Long> addWallet(@RequestBody WalletRequestDTO walletRequestDTO) {
        long walletId = walletService.addWallet(walletRequestDTO);
        return new ResponseEntity<>(walletId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDTO> getWalletById(@PathVariable("id") long walletId) {
        WalletResponseDTO walletResponseDTO = walletService.getWalletById(walletId);
        return new ResponseEntity<>(walletResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/reduceFunds/{id}")
    public ResponseEntity<Void> reduceFunds(@PathVariable("id") long walletId, @RequestParam long quantity) {
        walletService.reduceFunds(walletId, Math.abs(quantity));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/increaseFunds/{id}")
    public ResponseEntity<Void> increaseFunds(HttpServletRequest request, @PathVariable("id") long walletId, @RequestParam long quantity) {
        String path = (String) request.getRequestURI(); // Sa uzetom putanjom pa logika racunanja da bude na osnovu URI
        walletService.increaseFunds(walletId,Math.abs(quantity));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWalletById(long walletId){
        walletService.deleteUserAndWallet(walletId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
