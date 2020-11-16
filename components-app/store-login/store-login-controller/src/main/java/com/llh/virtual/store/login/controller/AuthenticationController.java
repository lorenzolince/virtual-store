/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.login.controller;

import com.llh.virtual.store.login.dao.repository.UserRepository;
import com.llh.virtual.store.login.service.JwtTokenProvider;
import com.llh.virtual.store.login.service.dto.AuthenticationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author lorenzo
 */
@RestController
@CrossOrigin(origins = "*", maxAge=3600)
@RequestMapping("api/auth")
public class AuthenticationController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @PostMapping("/login")
    public ResponseEntity signin(@RequestBody AuthenticationRequestDto data) {

        try {
            LOGGER.info("#########  signin  ############## ");
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", "Bearer " + token);
            LOGGER.info("#########  success  ############## ");
            return ok(model);
        } catch (AuthenticationException e) {
            LOGGER.info("#########  Invalid username/password supplied  ############## ");
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
