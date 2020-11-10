/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.login.dao.repository;

import com.llh.virtual.store.login.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author lorenzo
 */
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);

}
