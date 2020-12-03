/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.controller;

import com.llh.virtual.store.home.service.IventasService;
import com.llh.virtual.store.home.service.dto.VentasDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
