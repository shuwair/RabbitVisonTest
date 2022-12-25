package com.rabbitvission.demo.config;

import com.rabbitvission.demo.security.CustomAuthenticationSuccessHandler;
import com.rabbitvission.demo.security.JsonAuthenticationFilter;
import com.rabbitvission.demo.service.DbUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    DbUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        http
                .addFilterBefore(authenticationFilter(authenticationConfiguration),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/api/listUserAccounts").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET, "/api/userAccountsDetails").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET, "/api/listAllAccounts").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/api/listAllUsers").hasAuthority("USER")
                .and()
                .logout()
                .and()
                .csrf().disable()
                .httpBasic();
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

    @Bean
    public JsonAuthenticationFilter authenticationFilter(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        JsonAuthenticationFilter authFilter = new JsonAuthenticationFilter();
        authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
        authFilter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        authFilter.setAuthenticationSuccessHandler(successHandler());
        authFilter.setUsernameParameter("username");
        authFilter.setPasswordParameter("password");
        return authFilter;
    }

    @Bean
    public CustomAuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;

    }
}
