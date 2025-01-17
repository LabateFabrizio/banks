package com.example.fabrick.producingwebservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fabrick.producingwebservice.model.Account;
import com.example.fabrick.producingwebservice.model.MoneyTransfer;
import com.example.fabrick.producingwebservice.model.Transaction;
import com.example.fabrick.producingwebservice.service.AccountService;

@RestController
@RequestMapping("/api/gbs/banking/v4.0/accounts")
public class BankingController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = accountService.getAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountBalance(@PathVariable Long accountId) {
        Account account = accountService.getAccountBalance(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/{accountId}/payments/money-transfers")
    public ResponseEntity<MoneyTransfer> makeMoneyTransfer(@PathVariable Long accountId, @RequestBody MoneyTransfer moneyTransfer) {
        MoneyTransfer result = accountService.makeMoneyTransfer(accountId, moneyTransfer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long accountId) {
        List<Transaction> transactions = accountService.getTransactions(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
