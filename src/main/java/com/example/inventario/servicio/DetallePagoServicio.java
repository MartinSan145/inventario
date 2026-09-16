package com.example.inventario.servicio;

import com.example.inventario.entidad.Detalle_Pago;

import java.util.List;

public interface DetallePagoServicio {

    List<Detalle_Pago> listarDetalles();

    Detalle_Pago guardarDetalle(Detalle_Pago detalle);

    List<Detalle_Pago> listarPorPago(Long pagoId);

    List<Detalle_Pago> buscarDetallesPorPago(Long pagoId);
}
