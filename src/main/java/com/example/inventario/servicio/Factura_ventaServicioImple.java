package com.example.inventario.servicio;


import com.example.inventario.DTO.DetalleFacturaDTO;
import com.example.inventario.entidad.Factura_venta;
import com.example.inventario.entidad.Timbrado;
import com.example.inventario.repositorio.Factura_ventaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inventario.entidad.Detalle_Factura;
import com.example.inventario.entidad.Productos;
import com.example.inventario.DTO.DetalleFacturaDTO;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import java.util.List;


@Service
public class Factura_ventaServicioImple implements Factura_ventaServicio{


    @Autowired
    private Factura_ventaRepositorio facturaVentaRepositorio;
    @Autowired
    private ProductoServicio productoServicio;
    @Override
    public List<Factura_venta> listarFacturas() {
        return facturaVentaRepositorio.findAll();
    }

    @Override
    public List<Factura_venta> listarFacturasPendientes() {
       // return facturaVentaRepositorio.findAll();
        return facturaVentaRepositorio.findByEstado("EMITIDO");
    }

    @Override
    public Factura_venta obtenerTotalPorId(Long id) {
        return facturaVentaRepositorio.findById(id).get();
    }

    @Override
    public void guardarFactura(
            Factura_venta factura_venta,
            List<DetalleFacturaDTO> detalles) {

        Timbrado timbrado = factura_venta.getTimbrado();

        List<Factura_venta> facturas =
                facturaVentaRepositorio.findByTimbrado_Id(timbrado.getId());

        int siguiente = timbrado.getDesde();

        // Buscar el mayor correlativo utilizado
        for (Factura_venta factura : facturas) {

            String nro = factura.getNro_factura();

            if (nro != null && nro.length() >= 15) {

                String correlativo =
                        nro.substring(nro.lastIndexOf("-") + 1);

                int numero = Integer.parseInt(correlativo);

                if (numero >= siguiente) {
                    siguiente = numero + 1;
                }
            }
        }

        // Verificar límite del timbrado
        if (siguiente > timbrado.getHasta()) {
            throw new RuntimeException(
                    "El timbrado llegó al límite de facturas permitido."
            );
        }

        // Generar número de factura
        String nroFactura = String.format(
                "%03d-%03d-%07d",
                timbrado.getCod_establecimiento(),
                timbrado.getExpedicion(),
                siguiente
        );

        factura_venta.setNro_factura(nroFactura);


        // ==========================================
        // GUARDAR CABECERA + DETALLES
        // ==========================================

        for (DetalleFacturaDTO dto : detalles) {

            Productos producto =
                    productoServicio.obtenerProductosPorId(
                            dto.getProductoId()
                    );

            Detalle_Factura detalle = new Detalle_Factura();

            detalle.setProducto(producto);
            detalle.setCantidad(dto.getCantidad());
            detalle.setPrecio_unitario(dto.getPrecio_unitario());
            detalle.setSubtotal(dto.getSubtotal());

            factura_venta.agregarDetalle(detalle);
        }

        facturaVentaRepositorio.save(factura_venta);
    }

    @Override
    @Transactional
    public void anularFactura(Long id) {
        facturaVentaRepositorio.anularFactura(id);
    }
}