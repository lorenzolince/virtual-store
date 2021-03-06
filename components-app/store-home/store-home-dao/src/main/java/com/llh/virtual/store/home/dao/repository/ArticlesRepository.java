/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.dao.repository;

import com.llh.virtual.store.home.domain.Articles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lorenzo
 */
public interface ArticlesRepository extends JpaRepository<Articles, Long> {
 
    List<Articles> findByCategoria(String categoria);
}
