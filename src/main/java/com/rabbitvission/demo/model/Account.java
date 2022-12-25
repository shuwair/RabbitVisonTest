package com.rabbitvission.demo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Account {

    @Id
    private Long id;
    private Long accountNumber;

    @ManyToMany(mappedBy = "accounts")
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
