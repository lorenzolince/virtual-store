/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.home.dao.repository;

import com.llh.virtual.store.home.domain.Ventas;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author llince
 */
public interface VentasRepository extends JpaRepository<Ventas, Long> {

    @Query(
            value = "SELECT p.id, \n"
            + "a.name as \"producto\",p.cantidad,a.precio as precio_unitario,(p.cantidad * a.precio) AS precio_por_cantidad,\n"
            + "a.precio_real,(a.precio - a.precio_real) as ganancia_unitaria ,\n"
            + " p.cantidad *(a.precio - a.precio_real) as ganancia_por_cantidad \n"
            + "  FROM ventas v\n"
            + "     inner join productos p on p.id_venta = v.id\n"
            + "	 inner join articles a on a.id = p.id_articulo"
            + " where  v.id =:id",
            nativeQuery = true)
    List<Map> findProductosByIdVentas(@Param("id") int id);

    @Query(
            value = "SELECT v.id ,v.nombre_comprador comprador ,v.celular,v.direccion,v.fecha,v.estado, v.total total_venta ,sum((p.cantidad * (a.precio - a.precio_real))) total_ganancia\n"
            + "    FROM ventas v\n"
            + "     inner join productos p on p.id_venta = v.id\n"
            + "	 inner join articles a on a.id = p.id_articulo\n"
            + "GROUP BY  v.id ,v.nombre_comprador ,v.celular,v.direccion,v.fecha,v.estado, v.total",
            nativeQuery = true)
    List<Map> findAllVentas();

    @Transactional
    @Modifying
    @Query(value = "update ventas set estado =:status  where id =:id ", nativeQuery = true)
    int updateEstadoVenta(@Param("status") String status, @Param("id") int id);
}
