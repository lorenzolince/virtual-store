/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.service;

import com.llh.virtual.store.home.service.dto.ArticlesDto;
import java.util.List;

/**
 *
 * @author lorenzo
 */
public interface IarticlesService {

    public void save(ArticlesDto articles);

    public void save(List<ArticlesDto> articles);

    public ArticlesDto get(Long id);

    public List<ArticlesDto> getAll();
    
    public List<ArticlesDto> getByCategory(String category);
}
