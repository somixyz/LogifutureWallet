package com.logifuture.userService.service;

import com.logifuture.userService.entity.User;
import com.logifuture.userService.exception.UserServiceCustomException;
import com.logifuture.userService.external.client.PaymentService;
import com.logifuture.userService.external.client.WalletService;
import com.logifuture.userService.external.request.PaymentRequest;
import com.logifuture.userService.external.request.WalletRequest;
import com.logifuture.userService.model.UserRequestDTO;
import com.logifuture.userService.model.UserResponseDTO;
import com.logifuture.userService.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private PaymentService paymentService;


    @Override
    public UserResponseDTO getUserDetails(long userId) {
        log.info("Details for user with id {} ", userId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserServiceCustomException("User not found for the user Id:" + userId,
                        "NOT_FOUND", 404));

        UserResponseDTO.WalletDetails walletDetails = walletService.getWalletById(user.getWalletId()).getBody();

        return UserResponseDTO.builder().
                userId(user.getUserId())
                .userName(user.getUsername())
                .transactionDate(user.getTransactionDate())
                .walletId(user.getWalletId())
                .walletDetails(walletDetails)
                .build();
    }

    @Override
    public long addUser(UserRequestDTO userRequestDTO) {
        log.info("Creating user");

        User user = User.builder()
                .username(userRequestDTO.getUserName())
                .transactionDate(Instant.now())
                .walletId(userRequestDTO.getWalletId())
                .build();
        userRepository.save(user);
        log.info("User created");
        return user.getUserId();
    }

    @Transactional
    @Override
    public long addUserWithWallet(UserRequestDTO userRequestDTO) {
        log.info("Creating user with wallet");

        Long walletId = walletService.addWallet(
                WalletRequest.builder()
                        .walletName(userRequestDTO.getWalletName())
                        .balance(userRequestDTO.getBalance())
                        .build()).getBody();
        log.info("Wallet created");

        User user = User.builder()
                .username(userRequestDTO.getUserName())
                .transactionDate(Instant.now())
                .walletId(walletId)
                .build();
        userRepository.save(user);
        log.info("User created");
        return user.getUserId();
    }


    @Override
    public ResponseEntity<List<UserResponseDTO.PaymentDetails>> getAllUserTransactions(long userId) {
        log.info("All transaction for user id with : {} calling payent service", userId);
        return paymentService.getAllTransactionsByUserId(userId);
    }

    @Transactional
    @Override
    public void deleteUserAndWallet(long userId) {
        log.info("delete user with id {} and his wallet ", userId);

        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserServiceCustomException("User with given id is not found", "USER_NOT_FOUND", 404));

        userRepository.deleteById(userId);
        walletService.deleteWalletById(user.getWalletId());
        log.info("Succesfully deleted user with id {} and his wallet with wallet id {}", userId, user.getWalletId());
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        log.info("Return all users");
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(user -> {
                    UserResponseDTO userDTO = new UserResponseDTO();
                    BeanUtils.copyProperties(user, userDTO);
                    return userDTO;
                })
                .toList();
    }


    @Transactional
    @Override
    public long decreaseFunds(UserRequestDTO userRequestDTO, long userId) {

        log.info("Decrese funds for user {} and wallet with id {}", userRequestDTO.getUserName(), userRequestDTO.getWalletId());

        // Wallet Service - Blokiraj wallet (Smanji količinu)
        // Payment Service -> TRANSAKCIJA -> Success-> COMPLETE

        walletService.reduceFunds(userRequestDTO.getWalletId(), userRequestDTO.getQuantity());

        log.info("Reduced funds succesfully for wallet id {}", userRequestDTO.getWalletId());

        PaymentRequest paymentRequest
                = PaymentRequest.builder()
                .userId(userId)
                .paymentMode(userRequestDTO.getPaymentMode())
                .amount(userRequestDTO.getQuantity())
                .build();

        log.info("Calling Payment Service to complete the payment");

        Long id = paymentService.doPayment(paymentRequest).getBody();

        log.info("Payment complited");
        return id;
    }


}
