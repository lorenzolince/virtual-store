/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.controller;

import com.llh.virtual.store.home.service.IarticlesService;
import com.llh.virtual.store.home.service.dto.ArticlesDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lorenzo
 */
@RestController
@RequestMapping("api/articles")
public class ArticlesController {

    @Autowired
    private IarticlesService articlesService;

    @PostMapping("/save")
    public void save(@RequestBody ArticlesDto article) {
        articlesService.save(article);
    }

    @PostMapping("/save/multiple")
    public void saveMultiple(@RequestBody List<ArticlesDto> articles) {
        articlesService.save(articles);
    }

    @GetMapping("/get")
    public ArticlesDto get(@RequestParam(name = "id", required = false) Long id) {
        return articlesService.get(id);
    }

    @GetMapping("/get/all")
    public List<ArticlesDto> getAll() {
        return articlesService.getAll();
    }
}
