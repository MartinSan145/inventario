package com.example.inventario.servicio;

import com.example.inventario.DTO.DetallePagoDTO;
import com.example.inventario.entidad.Pagos;

import java.util.List;

public interface PagosServicio {
    public List <Pagos> listartodolospagos();

    Pagos guardarPago(
            Long facturaId,
            List<DetallePagoDTO> detalles);

    List<Pagos> listarPagosPorFactura(Long facturaId);

    Integer obtenerTotalPagado(Long facturaId);

}
