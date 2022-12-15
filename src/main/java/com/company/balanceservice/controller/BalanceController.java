package com.company.balanceservice.controller;

import com.company.balanceservice.entity.MessageResponse;
import com.company.balanceservice.entity.PostBalanceRequest;
import com.company.balanceservice.service.BalanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class BalanceController {

    @Autowired
    BalanceServiceImpl balanceService;

    @GetMapping(value = "/{id}")
    ResponseEntity<?> getBalance(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(balanceService.getBalance(id));
    }

    @PostMapping(value = "/{id}")
    ResponseEntity<?> postBalance(@RequestBody PostBalanceRequest postBalanceRequest) {
        balanceService.changeBalance(postBalanceRequest.getId(), postBalanceRequest.getValue());
        return ResponseEntity.ok().body(new MessageResponse("Account balance with id " + postBalanceRequest.getId() + " was updated"));
    }
}
