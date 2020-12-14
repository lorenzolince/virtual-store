/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.service.impl;

import com.llh.virtual.store.home.dao.repository.ArticlesRepository;
import com.llh.virtual.store.home.domain.Articles;
import com.llh.virtual.store.home.service.IarticlesService;
import com.llh.virtual.store.home.service.dto.ArticlesDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lorenzo
 */
@Service
public class ArticlesService implements IarticlesService {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Override
    public void save(ArticlesDto articles) {
        Articles article = new Articles();
        article.setId(articles.getId());
        article.setName(articles.getName());
        article.setUrl(articles.getUrl());
        article.setVideo(articles.getVideo());
        article.setDescription(articles.getDescription());
        article.setPrecio(articles.getPrecio());
        article.setPrecioReal(articles.getPrecioReal());
        article.setCategoria(articles.getCategoria());
        articlesRepository.save(article);
    }

    @Override
    public void save(List<ArticlesDto> articles) {
        List<Articles> listArticle = new ArrayList<>();
        articles.forEach(arti -> {
            Articles article = new Articles();
            article.setId(arti.getId());
            article.setName(arti.getName());
            article.setUrl(arti.getUrl());
            article.setVideo(arti.getVideo());
            article.setDescription(arti.getDescription());
            article.setPrecio(arti.getPrecio());
            article.setPrecioReal(arti.getPrecioReal());
            article.setCategoria(arti.getCategoria());
            listArticle.add(article);
        });
        articlesRepository.saveAll(listArticle);
    }

    @Override
    public ArticlesDto get(Long id) {
        Articles article = articlesRepository.findById(id).get();
        return new ArticlesDto()
                .setId(article.getId())
                .setName(article.getName())
                .setUrl(article.getUrl())
                .setVideo(article.getVideo())
                .setDescription(article.getDescription())
                .setPrecio(article.getPrecio())
                .setPrecioReal(article.getPrecioReal())
                .setCategoria(article.getCategoria());
    }

    @Override
    public List<ArticlesDto> getAll() {
        return articlesRepository.findAll()
                .stream()
                .map(article -> new ArticlesDto()
                        .setId(article.getId())
                        .setName(article.getName())
                        .setUrl(article.getUrl())
                        .setVideo(article.getVideo())
                        .setDescription(article.getDescription())
                        .setPrecio(article.getPrecio())
                        .setPrecioReal(article.getPrecioReal())
                        .setCategoria(article.getCategoria()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticlesDto> getByCategory(String category) {
       return articlesRepository.findByCategoria(category)
                .stream()
                .map(article -> new ArticlesDto()
                        .setId(article.getId())
                        .setName(article.getName())
                        .setUrl(article.getUrl())
                        .setVideo(article.getVideo())
                        .setDescription(article.getDescription())
                        .setPrecio(article.getPrecio())
                        .setCategoria(article.getCategoria()))
                .collect(Collectors.toList());
    }

}
