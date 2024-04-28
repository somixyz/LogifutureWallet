package com.logifuture.userService.controller;


import com.logifuture.userService.external.client.WalletService;
import com.logifuture.userService.model.UserRequestDTO;
import com.logifuture.userService.model.UserResponseDTO;
import com.logifuture.userService.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping("/{userId}/decreaseFunds")
    public ResponseEntity<Long> decreaseFunds(@RequestBody UserRequestDTO userRequestDTO, @PathVariable long userId) {
        long id = userService.decreaseFunds(userRequestDTO, userId);
        log.info("User Id: {} transaction payment done and checked user id {}", userId, id);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Long> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        long userId = userService.addUser(userRequestDTO);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/userWithWallet")
    public ResponseEntity<Long> addUserWithWallet(@RequestBody UserRequestDTO userRequestDTO) {
        long userId = userService.addUserWithWallet(userRequestDTO);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserDetails(@PathVariable long userId) {
        UserResponseDTO userResponseDTO = userService.getUserDetails(userId);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<UserResponseDTO.PaymentDetails>> getAllUserTransactions(@PathVariable long userId) {
        ResponseEntity<List<UserResponseDTO.PaymentDetails>> userResponseDTO = userService.getAllUserTransactions(userId);
        return new ResponseEntity<>(userResponseDTO.getBody(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserAndWallet(long userId){
        userService.deleteUserAndWallet(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> allUsers(){
        List<UserResponseDTO> userResponseDTOS = userService.getAllUsers();
        return new ResponseEntity<>(userResponseDTOS, HttpStatus.OK);
    }

}
