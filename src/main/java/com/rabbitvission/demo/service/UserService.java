package com.rabbitvission.demo.service;

import com.rabbitvission.demo.model.User;
import com.rabbitvission.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> findAllUsers() {
        return repository.findAll();
    }
}
