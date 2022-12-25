package com.rabbitvission.demo.service;

import com.rabbitvission.demo.model.Account;
import com.rabbitvission.demo.model.User;
import com.rabbitvission.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    public List<Account> findByUserByName(String name) {
        return repository.findByUsers_name(name);
    }

    public Account findByAccountNumberAndName(Long id, String name) {
        return repository.findByIdAndUsers_name(id, name);
    }

    public List<Account> findAllAccounts() {
        return repository.findAll();
    }
}
