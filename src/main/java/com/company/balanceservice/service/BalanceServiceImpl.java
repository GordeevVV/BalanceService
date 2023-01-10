package com.company.balanceservice.service;

import com.company.balanceservice.entity.BankAccount;
import com.company.balanceservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final
    BankAccountRepository accountRepository;

    public BalanceServiceImpl(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public BankAccount createBalance(Long value) {
        return accountRepository.save(new BankAccount(value));
    }

    @Override
    public Optional<Long> getBalance(Long id) {
        return Optional.of(accountRepository.findById(id).orElseThrow(() -> new RuntimeException("No such account with ID: " + id)).getBalance());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public synchronized void changeBalance(Long id, Long value) {
        BankAccount bankAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("No such account with ID: " + id));
        bankAccount.setBalance(bankAccount.getBalance() + value);
        accountRepository.save(bankAccount);
    }
}
