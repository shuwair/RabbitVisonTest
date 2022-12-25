package com.rabbitvission.demo.repository;


import com.rabbitvission.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@org.springframework.stereotype.Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUsers_name(String name);

    Account findByIdAndUsers_name(Long id, String name);

}
