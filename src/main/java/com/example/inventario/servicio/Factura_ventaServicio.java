package com.example.inventario.servicio;


import com.example.inventario.DTO.DetalleFacturaDTO;
import com.example.inventario.entidad.Factura_venta;

import java.util.List;


public interface Factura_ventaServicio {

    public List<Factura_venta> listarFacturas();

    public List<Factura_venta> listarFacturasPendientes();

    public Factura_venta obtenerTotalPorId(Long id);

   public void guardarFactura(Factura_venta factura_venta, List<DetalleFacturaDTO> detalles);

    public void anularFactura(Long id);
}
