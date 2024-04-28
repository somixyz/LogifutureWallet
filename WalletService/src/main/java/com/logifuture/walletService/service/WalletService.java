package com.logifuture.walletService.service;

import com.logifuture.walletService.model.WalletRequestDTO;
import com.logifuture.walletService.model.WalletResponseDTO;

public interface WalletService {

    long addWallet(WalletRequestDTO walletRequestDTO);

    WalletResponseDTO getWalletById(long walletId);

    void reduceFunds(long walletId, long quantity);

    void increaseFunds(long walletId, long quantity);

    void deleteUserAndWallet(long walletId);
}
