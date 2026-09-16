package com.example.inventario.repositorio;

import com.example.inventario.entidad.movimiento_stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoStock_repositorio extends JpaRepository<movimiento_stock, Long> {
  //  List<movimiento_stock> findAllByOrderByFechaDesc();

    List<movimiento_stock> findByProductos_IdOrderByFechaDesc(
            Long idProducto
    );

    List<movimiento_stock> findAllByOrderByFechaDesc();
}
