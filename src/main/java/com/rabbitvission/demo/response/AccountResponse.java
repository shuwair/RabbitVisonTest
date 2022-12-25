package com.rabbitvission.demo.response;

import com.rabbitvission.demo.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountResponse {

    public AccountResponse(List<Account> accountList) {
        this.accountList = accountList;

    }

    public AccountResponse(Account account) {
        accountList = new ArrayList<>();
        accountList.add(account);
    }

    private List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
