package com.rabbitvission.demo.controller;


import com.rabbitvission.demo.model.Account;
import com.rabbitvission.demo.model.User;
import com.rabbitvission.demo.response.AccountResponse;
import com.rabbitvission.demo.response.UserResponse;
import com.rabbitvission.demo.service.AccountService;
import com.rabbitvission.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listUserAccounts", method = RequestMethod.GET)
    public AccountResponse getUserAccounts() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Account> accountList = accountService.findByUserByName(auth.getName());
        AccountResponse accountResponse = new AccountResponse(accountList);
        return accountResponse;

    }

    @RequestMapping(value = "/userAccountsDetails", method = RequestMethod.GET)
    public AccountResponse getUserAccountDetail(@RequestBody Account account) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account accountDetail = accountService.findByAccountNumberAndName(account.getId(), auth.getName());
        AccountResponse accountResponse = new AccountResponse(accountDetail);
        return accountResponse;

    }


    @RequestMapping(value = "/listAllAccounts", method = RequestMethod.GET)
    public AccountResponse getAllAccounts() {
        List<Account> accountList = accountService.findAllAccounts();
        AccountResponse accountResponse = new AccountResponse(accountList);
        return accountResponse;

    }

    @RequestMapping(value = "/listAllUsers", method = RequestMethod.GET)
    public UserResponse getAllUsers() {
        List<User> userList = userService.findAllUsers();
        UserResponse userResponse = new UserResponse(userList);
        return userResponse;
    }

    @RequestMapping(value = "/performTransaction", method = RequestMethod.GET)
    public AccountResponse perfromTransactions(@RequestBody Account account) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //ToDo : add implementaiotn for transactions based on login user details

        return null;

    }


}
