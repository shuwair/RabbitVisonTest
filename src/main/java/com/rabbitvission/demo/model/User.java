package com.rabbitvission.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DEMOUSER")
public class User {

    @Id
    private Long id;
    private String name;
    private String password;
    private String role;

    @ManyToMany
    @JoinTable(
            name = "USERACCOUNT",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "accountNumber"))
    private Set<Account> accounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /*public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }*/
}
