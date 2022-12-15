package com.company.balanceservice.controller;

import com.company.balanceservice.entity.BankAccount;
import com.company.balanceservice.entity.MessageResponse;
import com.company.balanceservice.service.BalanceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1")
public class BalanceController {

    private final BalanceServiceImpl balanceService;

    public BalanceController(BalanceServiceImpl balanceService) {
        this.balanceService = balanceService;
    }
    @PostMapping(value = "account")
    ResponseEntity<BankAccount> createBalance(@RequestBody Long value){
        BankAccount savedBankAccount = balanceService.createBalance(value);
        return new ResponseEntity<>(savedBankAccount, HttpStatus.CREATED);
    }
    @GetMapping(value = "account/{id}")
    ResponseEntity<Long> getBalance(@PathVariable("id") long id) {
        return new ResponseEntity<>(balanceService.getBalance(id).get(), HttpStatus.OK);
    }

    @PutMapping(value = "account/{id}")
    ResponseEntity<MessageResponse> changeBalance(@RequestBody BankAccount bankAccount) {
        balanceService.changeBalance(bankAccount.getId(), bankAccount.getBalance());
        return new ResponseEntity<>(new MessageResponse("Account balance with id " + bankAccount.getId() + " was updated"), HttpStatus.OK);
    }
}
