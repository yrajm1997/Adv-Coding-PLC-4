package com.bankmgmt.app.service;

import com.bankmgmt.app.entity.Account;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;

@Service
public class BankService {

    private List<Account> accounts = new ArrayList<>();
    private Integer currentId = 1;

    // Create Account
    public Account createAccount(String accountHolderName, String accountType, String email) {
        Account newAccount = new Account(currentId++, accountHolderName, accountType, 0.0, email);
        accounts.add(newAccount);
        return newAccount;
    }

    // Get All Accounts
    public List<Account> getAllAccounts() {
        return accounts;
    }

    public Account findAccountById(Integer id) {
        for (Account a: accounts) {
            if (a.getId().intValue() == id.intValue()) {
                return a;
            }
        }
        return null;
    }

    // Get Account by ID
    public Account getAccountById(Integer id) {
        return findAccountById(id);
    }

    // Deposit Money
    public Account deposit(Integer id, Double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero");
            return null;
        }

        Account account = findAccountById(id);
        if (account == null) {
            System.out.println("Account not found");
            return null;
        }

        account.setBalance(account.getBalance() + amount);
        return account;
    }

    // Withdraw Money
    public Account withdraw(Integer id, Double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero");
            return null;
        }

        Account account = findAccountById(id);
        if (account == null) {
            System.out.println("Account not found");
            return null;
        }

        if (account.getBalance() < amount) {
            System.out.println("Insufficient funds");
            return null;
        }

        account.setBalance(account.getBalance() - amount);
        return account;
    }

    // Transfer Money
    public List<Account> transfer(Integer fromId, Integer toId, Double amount) {
        Account fromAccount = findAccountById(fromId);
        Account toAccount = findAccountById(toId);

        if (fromAccount == null || toAccount == null) {
            System.out.println("Account not found");
            return null;
        }

        if (fromAccount.getBalance() < amount) {
            System.out.println("Insufficient funds in the source account");
            return null;
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        return Arrays.asList(fromAccount, toAccount);
    }

    // Delete Account
    public String deleteAccount(Integer id) {
        Account account = findAccountById(id);
        if (account == null) {
            return "Account not found";
        }
        accounts.remove(account);
        return "Account Deleted Successfully";
    }
}
