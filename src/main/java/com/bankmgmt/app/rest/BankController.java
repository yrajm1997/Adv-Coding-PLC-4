package com.bankmgmt.app.rest;

import com.bankmgmt.app.entity.Account;
import com.bankmgmt.app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankController {

    @Autowired
    private BankService bankService;

    // Create a new account
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account a = bankService.createAccount(account.getAccountHolderName(), account.getAccountType(), account.getEmail());
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = bankService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Get an account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) {
        Account a = bankService.getAccountById(id);
        if (a != null) {
            return new ResponseEntity<>(a, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Deposit money
    @PostMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Integer id, @RequestBody Double amount) {
        Account a = bankService.deposit(id, amount);
        if (a == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    // Withdraw money
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Integer id, @RequestBody Double amount) {
        Account a = bankService.withdraw(id, amount);
        if (a == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    // Transfer money between accounts
    @PostMapping("/transfer")
    public ResponseEntity<List<Account>> transfer(@RequestParam("fromId") Integer fromId, @RequestParam("toId") Integer toId, @RequestParam("amount") Double amount) {
        List<Account> accounts = bankService.transfer(fromId, toId, amount);
        if (accounts == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Delete an account
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
        String message = bankService.deleteAccount(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
