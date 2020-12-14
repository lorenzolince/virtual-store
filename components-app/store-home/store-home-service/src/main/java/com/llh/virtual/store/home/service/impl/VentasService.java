/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.service.impl;

import com.llh.virtual.store.home.dao.repository.ArticlesRepository;
import com.llh.virtual.store.home.dao.repository.ProductosRepository;
import com.llh.virtual.store.home.dao.repository.VentasRepository;
import com.llh.virtual.store.home.domain.Productos;
import com.llh.virtual.store.home.domain.Ventas;
import com.llh.virtual.store.home.service.IventasService;
import com.llh.virtual.store.home.service.dto.ProductosDto;
import com.llh.virtual.store.home.service.dto.VentasDto;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author llince
 */
@Service
public class VentasService implements IventasService {

    @Autowired
    private VentasRepository ventasRepository;
    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public void save(VentasDto ventas) {
        Ventas venta = new Ventas();
        venta.setFecha(new Date(System.currentTimeMillis()));
        venta.setNombreComprador(ventas.getNombreComprador());
        venta.setCelular(ventas.getCelular());
        venta.setDireccion(ventas.getDireccion());
        List<Productos> listaProductos = new ArrayList<>();
        ventas.getProductos().forEach(p -> {
            Productos pro = new Productos();
            pro.setCantidad(p.getCantidad());
            pro.setArticle(articlesRepository.findById(p.getIdArticles()).get());
            listaProductos.add(pro);
        });
        venta.setProductos(listaProductos);
        venta.setStatus(Ventas.Status.valueOf(ventas.getStatus().name()));
        venta.setTotal(ventas.getTotal());
        ventasRepository.save(venta);
        productosRepository.saveAll(venta.getProductos()
                .stream()
                .map(p -> {
                    Productos pro = new Productos();
                    pro.setVenta(venta);
                    pro.setCantidad(p.getCantidad());
                    pro.setArticle(p.getArticle());
                    return pro;
                })
                .collect(Collectors.toList()));
    }

    @Override
    public List<VentasDto> getAll() {
        return ventasRepository.findAll()
                .stream()
                .map(venta -> new VentasDto()
                        .setFecha(venta.getFecha())
                        .setId(venta.getId())
                        .setNombreComprador(venta.getNombreComprador())
                        .setCelular(venta.getCelular())
                        .setDireccion(venta.getDireccion())
                        .setStatus(VentasDto.Status.valueOf(venta.getStatus().name()))
                        .setTotal(venta.getTotal())
                        .setProductos(venta.getProductos()
                                .stream()
                                .map(pro -> new ProductosDto()
                                        .setCantidad(pro.getCantidad())
                                        .setIdArticles(pro.getArticle().getId()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Map> getAllVentas() {
        return ventasRepository.findAllVentas();
    }

    @Override
    public List<Map> getProductosByVenta(int id) {
        return ventasRepository.findProductosByIdVentas(id);
    }

    @Override
    public int updateEstadoVenta(String status, int id) {
        return ventasRepository.updateEstadoVenta(status, id);
    }

}
