/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lorenzo
 */
@RestController()
@CrossOrigin(origins = "*", maxAge=3600)
@RequestMapping("api/user")
public class UserinfoController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("rawtypes")
    @GetMapping("me")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        LOGGER.info("  currentUser ");
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }
}
