package com.logifuture.userService.service;

import com.logifuture.userService.model.UserRequestDTO;
import com.logifuture.userService.model.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {



    UserResponseDTO getUserDetails(long userId);

    long addUser(UserRequestDTO userRequestDTO);

    long decreaseFunds(UserRequestDTO userRequestDTO, long userId);

    long addUserWithWallet(UserRequestDTO userRequestDTO);

    ResponseEntity<List<UserResponseDTO.PaymentDetails>> getAllUserTransactions(long userId);

    void deleteUserAndWallet(long userId);

    List<UserResponseDTO> getAllUsers();

}
