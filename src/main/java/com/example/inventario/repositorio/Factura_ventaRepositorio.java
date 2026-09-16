package com.example.inventario.repositorio;


import com.example.inventario.entidad.Factura_venta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Factura_ventaRepositorio extends JpaRepository <Factura_venta, Long> {

    List<Factura_venta> findByTimbrado_Id(Long timbradoId);

    List<Factura_venta> findByEstado(String estado);

    @Modifying
    @Transactional
    @Query("UPDATE Factura_venta f SET f.estado = 'ANULADA' WHERE f.id = :id")
    void anularFactura(@Param("id") Long id);
}
