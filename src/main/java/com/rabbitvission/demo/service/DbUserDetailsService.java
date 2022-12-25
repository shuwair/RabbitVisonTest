package com.rabbitvission.demo.service;

import com.rabbitvission.demo.model.User;
import com.rabbitvission.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class DbUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        final User user = userRepo.findByName(userName).get(0);
        String encPassword = (new BCryptPasswordEncoder().encode(user.getPassword()));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(),
                encPassword,
                AuthorityUtils.createAuthorityList(user.getRole()));
        return userDetails;
    }
}