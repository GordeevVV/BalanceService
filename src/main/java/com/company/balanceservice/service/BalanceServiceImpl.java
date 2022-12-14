package com.company.balanceservice.service;

import com.company.balanceservice.entity.BankAccount;
import com.company.balanceservice.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    AccountRepository accountRepository;


    @Override
    public Optional<Long> getBalance(Long id) {
        return Optional.of(accountRepository.findById(id).orElseThrow(() -> new RuntimeException("No such account with ID: " + id)).getBalance());
    }

    @Override
    @Transactional
    public void changeBalance(Long id, Long value) {
        BankAccount bankAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("No such account with ID: " + id));
        bankAccount.setBalance(bankAccount.getBalance() + value);
        accountRepository.save(bankAccount);
    }
}
