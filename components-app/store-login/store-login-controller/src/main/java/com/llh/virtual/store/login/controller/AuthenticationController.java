/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.login.controller;

import com.llh.virtual.store.login.dao.repository.UserRepository;
import com.llh.virtual.store.login.domain.User;
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
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.http.ResponseEntity.ok;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author lorenzo
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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
            Optional<User> userOptional = this.users.findByUsername(username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, userOptional.orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
            User user = userOptional.get();
            Map<Object, Object> model = new HashMap<>();
            Map<Object, Object> modelUser = new HashMap<>();
            modelUser.put("username", user.getUsername());
            modelUser.put("nombre", user.getNombre());
            modelUser.put("celular", user.getCelular());
            modelUser.put("direccion", user.getDireccion());
            model.put("user", modelUser);
            model.put("roles", user.getRoles());
            model.put("token", "Bearer " + token);
            LOGGER.info("#########  success  ############## ");
            return ok(model);
        } catch (AuthenticationException e) {
            LOGGER.info("#########  Invalid username/password supplied  ############## ");
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity logoutPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            LOGGER.info("#########  logout  ############## ");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            LOGGER.info("#########  success  ############## ");
            Thread.sleep(2000);

        } catch (Exception e) {
        }
        return ok("success");
    }
}
