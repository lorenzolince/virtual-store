/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.service.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author llince
 */
public class VentasDto {

    private static final long serialVersionUID = 3716143772023645465L;
    private Long id;

    private String nombreComprador;

    private Status status;

    private Date fecha;

    private String celular;

    private String direccion;

    private double total;

    private List<ProductosDto> productos = new ArrayList<>();

    public enum Status {

        PROCESO, VENDIDO, CANCELADO;
    }

    public Long getId() {
        return id;
    }

    public VentasDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public VentasDto setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public VentasDto setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Date getFecha() {
        return fecha;
    }

    public VentasDto setFecha(Date fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getCelular() {
        return celular;
    }

    public VentasDto setCelular(String celular) {
        this.celular = celular;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public VentasDto setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public VentasDto setTotal(double total) {
        this.total = total;
        return this;
    }

    public List<ProductosDto> getProductos() {
        return productos;
    }

    public VentasDto setProductos(List<ProductosDto> productos) {
        this.productos = productos;
        return this;
    }

}
