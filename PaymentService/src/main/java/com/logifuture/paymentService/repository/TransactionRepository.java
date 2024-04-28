package com.logifuture.paymentService.repository;


import com.logifuture.paymentService.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByUserId(Long aLong);

    List<Transaction> findAllByUserId(Long userId);
}
