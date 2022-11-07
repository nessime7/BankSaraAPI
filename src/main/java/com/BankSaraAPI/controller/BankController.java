package com.BankSaraAPI.controller;

import com.BankSaraAPI.model.*;
import com.BankSaraAPI.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/*
- account management
    - get/create/edit/delete account
    - transfer between accounts
 GET/POST/PUT/DELETE
 */

@RestController
public class BankController {

    private final BankService bankService;

    // dependency injection
    // jak będzie widział tą klasę to musi stworzyć sam tą klasę
    // gdzieś tam pod spodem, konstrukt taki, który nam stworzy tą klasę
    // BankController z bankService
    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    // wyświetlenie wszystkich kont
    // działa
    @GetMapping("accounts")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(bankService.getAccounts());
    }

    // stworzenie nowego konta
    // działa
    @PostMapping("accounts")
    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountRequest request) {
        bankService.createAccount(request);
        return ResponseEntity.noContent().build();
    }
//    @PostMapping("accounts")
//    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountRequest request) {
//        bankService.createAccount(request);
//        return ResponseEntity.noContent().build();
//    }

    // edit balance
    // działa
    @PutMapping("accounts/{id}/balance")
    public ResponseEntity<Account> editAccount(@PathVariable UUID id, @RequestBody EditAccountBalanceRequest request) {
        final var account = bankService.editAccount(id, request);
        return ResponseEntity.ok(account);
    }

    // edit currency
    // działa
    @PutMapping("accounts/{id}/currency")
    public ResponseEntity<Account> editCurrency(@PathVariable UUID id, @RequestBody EditAccountCurrencyRequest request) {
        final var account = bankService.changeCurrency(id, request);
        return ResponseEntity.ok(account);
    }

    // delete id
    // działa
    @DeleteMapping("accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        bankService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    // transfer
    @PostMapping("transfers")
    public ResponseEntity<Void> editAccount(@RequestBody AccountTransferRequest request) throws SQLException {
        bankService.transfer(request);
        return ResponseEntity.noContent().build();
    }
//    @PostMapping("transfers")
//    public ResponseEntity<Void> editAccount(@RequestBody AccountTransferRequest request) {
//        bankService.transfer(request);
//        return ResponseEntity.noContent().build();
//    }
}
