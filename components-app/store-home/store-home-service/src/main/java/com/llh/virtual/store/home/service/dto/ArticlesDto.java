/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.service.dto;

import java.io.Serializable;

/**
 *
 * @author lorenzo
 */
public class ArticlesDto implements Serializable {

    private static final long serialVersionUID = 3716143772023645465L;
    private Long id;
    private String name;
    private String url;
    private String video;
    private String description;
    private double precio;
    private String categoria;
    private double precioReal;
    private int cantidad;

    public Long getId() {
        return id;
    }

    public ArticlesDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArticlesDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ArticlesDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getVideo() {
        return video;
    }

    public ArticlesDto setVideo(String video) {
        this.video = video;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArticlesDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrecio() {
        return precio;
    }

    public ArticlesDto setPrecio(double precio) {
        this.precio = precio;
        return this;
    }

    public String getCategoria() {
        return categoria;
    }

    public ArticlesDto setCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public double getPrecioReal() {
        return precioReal;
    }

    public ArticlesDto setPrecioReal(double precioReal) {
        this.precioReal = precioReal;
        return this;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ArticlesDto setCantidad(int cantidad) {
        this.cantidad = cantidad;
        return this;
    }

  

}
