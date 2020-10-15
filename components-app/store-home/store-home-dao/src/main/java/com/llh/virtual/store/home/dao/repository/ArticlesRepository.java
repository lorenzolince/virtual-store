/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.dao.repository;

import com.llh.virtual.store.home.domain.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lorenzo
 */
@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {
    
}
