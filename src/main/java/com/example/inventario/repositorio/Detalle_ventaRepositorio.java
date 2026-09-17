package com.example.inventario.repositorio;

import com.example.inventario.entidad.Detalle_Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Detalle_ventaRepositorio extends JpaRepository<Detalle_Factura, Long> {


    @Query("SELECT d FROM Detalle_Factura d WHERE d.factura_venta.id = :facturaId")
    List<Detalle_Factura> buscarPorFactura(@Param("facturaId") Long facturaId);


}
