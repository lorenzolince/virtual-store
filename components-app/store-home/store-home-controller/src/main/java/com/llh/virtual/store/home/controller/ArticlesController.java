/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.controller;

import com.llh.virtual.store.home.service.IarticlesService;
import com.llh.virtual.store.home.service.dto.ArticlesDto;
import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lorenzo
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/articles")
public class ArticlesController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IarticlesService articlesService;

    @PostMapping("/save")
    public void save(@RequestBody ArticlesDto article) {
        articlesService.save(article);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam(name = "id", required = true) long id) {
        articlesService.delete(id);
    }

    @PostMapping("/save/file")
    public void saveMultiPart(@RequestParam(name = "name") String name,
            @RequestParam(name = "url") String url,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "video", required = false) String video,
            @RequestParam(name = "precio", required = true) double precio,
            @RequestParam(name = "precioReal", required = true) double precioReal,
            @RequestParam(name = "categoria", required = true) String categoria,
            @RequestParam(name = "cantidad", required = true) int cantidad,
            @ModelAttribute MultipartFile image) {
        ArticlesDto article = new ArticlesDto()
                .setName(name)
                .setUrl(url)
                .setDescription(description)
                .setVideo(video)
                .setPrecio(precio)
                .setPrecioReal(precioReal)
                .setCategoria(categoria)
                .setCantidad(cantidad);
        LOGGER.info(article
                .toString());
        saveFile(image);
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

    @GetMapping("/get/byCategory")
    public List<ArticlesDto> getByCategory(@RequestParam(name = "category", required = true) String category) {
        return articlesService.getByCategory(category);
    }

    private void saveFile(MultipartFile multipartFile) {

        try {
            if (multipartFile != null) {
                LOGGER.info("############### saveFile ####################");
                String destination = "content" + File.separator + "images" + File.separator + multipartFile.getOriginalFilename();
                File dir = new File("." + File.separator);
                File file = new File(dir.getCanonicalPath() + File.separator + destination);
                multipartFile.transferTo(file);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
