package com.logifuture.walletService.service;

import com.logifuture.walletService.entity.Wallet;
import com.logifuture.walletService.exception.WalletServiceCustomException;
import com.logifuture.walletService.model.WalletRequestDTO;
import com.logifuture.walletService.model.WalletResponseDTO;
import com.logifuture.walletService.repository.WalletRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("prototype")
@Log4j2
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;



    @Autowired
    public long addWallet(WalletRequestDTO walletRequestDTO) {
        log.info("Adding wallet");

        Wallet wallet = Wallet.builder().walletName(walletRequestDTO.getWalletName()).balance(walletRequestDTO.getBalance()).build();

        walletRepository.save(wallet);
        log.info("Wallet created");

        return wallet.getWalletId();
    }

    @Override
    public WalletResponseDTO getWalletById(long walletId) {
        log.info("Get the wallet for walletIdL {}", walletId);

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletServiceCustomException("Wallet with provided id not found", "WALLET_NOT_FOUND"));

        WalletResponseDTO walletResponseDTO = new WalletResponseDTO();
        BeanUtils.copyProperties(wallet, walletResponseDTO);

        return walletResponseDTO;
    }

    @Override
    public void reduceFunds(long walletId, long quantity) {
        log.info("Reduce funds for this amount {} for wallet with id {} ", quantity, walletId);

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletServiceCustomException("Wallet with given id not found", "WALLET_NOT_FOUND"));

        if (wallet.getBalance() < quantity)
            throw new WalletServiceCustomException("Wallet doesnt have sufficient funds for quantity", "INSUFFICIENT_QUANTITY");

        wallet.setBalance(wallet.getBalance() - quantity);
        walletRepository.save(wallet);
        log.info("Wallet funds updated successfully");
    }

    @Override
    public void increaseFunds(long walletId, long quantity) {
        log.info("Increase funds for this amount {} for wallet with id {}", quantity, walletId);

        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new WalletServiceCustomException("Wallet with given id not found", "WALLET_NOT_FOUND"));

        wallet.setBalance(wallet.getBalance() + quantity);
        walletRepository.save(wallet);
        log.info("Wallet funds updated successfully");
    }

    @Override
    public void deleteUserAndWallet(long walletId) {
        log.info("Deleting Wallet with id {}", walletId);
        walletRepository.deleteById(walletId);
        log.info("Wallet deleted");
    }


}
