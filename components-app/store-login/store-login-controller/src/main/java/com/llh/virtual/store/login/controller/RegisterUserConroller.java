/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.login.controller;

import com.llh.virtual.store.login.dao.repository.UserRepository;
import com.llh.virtual.store.login.domain.User;
import com.llh.virtual.store.login.service.dto.AuthenticationRequestDto;
import com.llh.virtual.store.login.service.dto.RegisterUserDto;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author llince
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/register")
public class RegisterUserConroller {
    
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserRepository users;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @PostMapping("")
    public ResponseEntity register(@RequestBody RegisterUserDto data) {
        User user = new User()
                .setCelular(data.getCelular())
                .setDireccion(data.getDireccion())
                .setNombre(data.getNombre())
                .setPassword(this.passwordEncoder.encode(data.getPassword()))
                .setUsername(data.getEmail())
                .setRoles(Arrays.asList("ROLE_USER"));
        User response = users.save(user);
        LOGGER.info("register success");
        return ok(response);
    }
}
