package com.rabbitvission.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitvission.demo.model.User;
import com.rabbitvission.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ObjectMapper objectMapper;

    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication auth
    ) throws IOException, ServletException {
        writeResponse(response);
    }

    private void writeResponse(HttpServletResponse response) throws IOException {
        response.setStatus(200);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(true);
        loginResponse.setUser(fetchCurrentUser());
        objectMapper.writeValue(response.getWriter(), loginResponse);
    }

    private User fetchCurrentUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return fetchUser(auth.getName());
    }

    private User fetchUser(String userName) {
        Objects.requireNonNull(userName);
        return Optional.ofNullable(userRepo.findByName(userName).get(0)).orElseThrow(() ->
                new IllegalArgumentException("User name not found " + userName));
    }
}