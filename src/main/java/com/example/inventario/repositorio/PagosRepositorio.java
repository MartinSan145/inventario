package com.example.inventario.repositorio;

import com.example.inventario.entidad.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagosRepositorio extends JpaRepository<Pagos, Long> {

    @Query("""
        SELECT p
        FROM Pagos p
        WHERE p.id_factura.id = :facturaId
    """)
    List<Pagos> listarPorFactura(@Param("facturaId") Long facturaId);


    @Query("""
        SELECT COALESCE(SUM(p.monto), 0)
        FROM Pagos p
        WHERE p.id_factura.id = :facturaId
    """)
    Integer sumarPagosPorFactura(@Param("facturaId") Long facturaId);
}