/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.service;

import com.llh.virtual.store.home.service.dto.VentasDto;
import java.util.List;

/**
 *
 * @author llince
 */
public interface IventasService {
    
     public void save(VentasDto ventas);
     
      public  List<VentasDto> getAll();
    
}
