package com.company.balanceservice;


import com.company.balanceservice.entity.BankAccount;
import com.company.balanceservice.repository.BankAccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BalanceServiceJpaTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    private BankAccount bankAccount;
    private BankAccount bankAccount1;

    @BeforeEach
    public void setUp() {
        bankAccount = new BankAccount(1000);
        bankAccount1 = new BankAccount(500);
    }

    @AfterEach
    public void tearDown() {
        bankAccountRepository.deleteAll();
        bankAccount = null;
        bankAccount1 = null;
    }

    @Test
    public void saveBankAccount() {
        bankAccountRepository.save(bankAccount);
        BankAccount fetched = bankAccountRepository.findById(bankAccount.getId()).get();
        assertThat(bankAccount).usingRecursiveComparison().ignoringFields("id").isEqualTo(fetched);
    }

    @Test
    public void findByIdBankAccount() {
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        Assertions.assertEquals(savedBankAccount, bankAccountRepository.findById(savedBankAccount.getId()).get());
    }

    @Test
    public void findAllBankAccountTest() {
        bankAccountRepository.save(bankAccount);
        bankAccountRepository.save(bankAccount1);
        assertThat(bankAccountRepository.findAll()).asList().size().isEqualTo(2);
    }

}
