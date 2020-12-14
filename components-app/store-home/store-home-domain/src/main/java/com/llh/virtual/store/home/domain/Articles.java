/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author lorenzo
 */
@Entity
@Table(name = "articles")
@SequenceGenerator(name = "SequenceArticles",
        sequenceName = "s_articles", allocationSize = 1)
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SequenceArticles")
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;
    @Column(name = "video")
    private String video;
    @Column(name = "description")
    private String description;
    @Column(name = "precio")
    private double precio;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "precio_real")
    private double precioReal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecioReal() {
        return precioReal;
    }

    public void setPrecioReal(double precioReal) {
        this.precioReal = precioReal;
    }

}
