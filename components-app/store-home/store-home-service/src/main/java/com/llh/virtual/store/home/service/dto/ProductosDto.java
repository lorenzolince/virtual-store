/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.service.dto;

/**
 *
 * @author llince
 */
public class ProductosDto {

    private Long idArticles;
    private int cantidad;

    public Long getIdArticles() {
        return idArticles;
    }

    public ProductosDto setIdArticles(Long idArticles) {
        this.idArticles = idArticles;
        return this;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ProductosDto setCantidad(int cantidad) {
        this.cantidad = cantidad;
        return this;
    }

}
