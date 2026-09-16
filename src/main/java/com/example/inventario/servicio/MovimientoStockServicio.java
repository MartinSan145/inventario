package com.example.inventario.servicio;

import com.example.inventario.entidad.movimiento_stock;

import java.util.List;

public interface MovimientoStockServicio {



    void registrarMovimiento(Integer idProducto, String tipoMovimiento, Integer cantidad, String motivo);

    void registrarMovimiento(
            Long idProducto,
            String tipoMovimiento,
            Integer cantidad,
            String motivo
    );

    List<movimiento_stock> listarMovimientos();

    List<movimiento_stock> listarMovimientosProducto(
            Long idProducto
    );

}

