/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.controller;

import com.llh.virtual.store.home.service.IventasService;
import com.llh.virtual.store.home.service.dto.VentasDto;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author llince
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/ventas")
public class VentasController {

    @Autowired
    private IventasService ventasService;

    @PostMapping("/save")
    public void save(@RequestBody VentasDto ventas) {
        ventasService.save(ventas);
    }

    @GetMapping("/get/all")
    public List<VentasDto> getAll() {
        return ventasService.getAll();
    }

    @GetMapping("/get/estados")
    public List<VentasDto.Status> geEstados() {
        return Arrays.asList(VentasDto.Status.values());
    }

    @GetMapping("/get/ventas")
    public List<Map> getAllVentas() {
        return ventasService.getAllVentas();
    }

    @GetMapping("/get/productos/{id}")
    public List<Map> productos(@PathVariable("id") int id) {
        return ventasService.getProductosByVenta(id);
    }

    @PostMapping("/update/Estado/{id}/{estado}")
    public int updateEstadoVenta(@PathVariable("id") int id, @PathVariable("estado") VentasDto.Status estado) {
        return ventasService.updateEstadoVenta(estado, id);
    }
}
