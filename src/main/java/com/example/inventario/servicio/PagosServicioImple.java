package com.example.inventario.servicio;

import com.example.inventario.DTO.DetallePagoDTO;
import com.example.inventario.entidad.*;
import com.example.inventario.repositorio.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PagosServicioImple implements PagosServicio {

    @Autowired
    private PagosRepositorio pagosRepositorio;

    @Autowired
    private Factura_ventaRepositorio facturaVentaRepositorio;

    @Autowired
    private TipoPagoRepositorio tipoPagoRepositorio;

    @Autowired
    private MovimientoStockServicio movimientoStockServicio;

    @Autowired
    Detalle_ventaRepositorio detalleVentaRepositorio;

    @Override

    public List<Pagos> listartodolospagos() {
        return pagosRepositorio.findAll();
    }

    @Override
    @Transactional
    public Pagos guardarPago(Long facturaId, List<DetallePagoDTO> detalles) {
        Factura_venta factura = facturaVentaRepositorio.findById(facturaId).orElseThrow(() ->
                new RuntimeException("Factura no encontrada"));

        if (detalles == null || detalles.isEmpty()) {
            throw new RuntimeException("Debe agregar al menos un medio de pago");
        }

        Integer totalPagado = pagosRepositorio.sumarPagosPorFactura(facturaId);

        Integer saldo = factura.getTotal() - totalPagado;

        int totalNuevoPago = 0;

        for (DetallePagoDTO dto : detalles) {

            if (dto.getMonto() == null || dto.getMonto() <= 0) {

                throw new RuntimeException("Todos los montos deben ser mayores a cero");
            }

            totalNuevoPago += dto.getMonto();
        }

        if (totalNuevoPago > saldo) {

            throw new RuntimeException("El monto supera el saldo pendiente. " +
                    "Saldo disponible: " + saldo);
        }

        Pagos pago = new Pagos();

        pago.setId_factura(factura);
        pago.setMonto(totalNuevoPago);
        pago.setUsuario("admin");

        for (DetallePagoDTO dto : detalles) {

            Tipo_pago tipoPago =
                    tipoPagoRepositorio.findById(dto.getTipoPagoId()).orElseThrow(() ->
                            new RuntimeException("Tipo de pago no encontrado"));

            Detalle_Pago detalle = new Detalle_Pago();

            detalle.setTipo_pago(tipoPago);
            detalle.setMonto(dto.getMonto());
            detalle.setDetalle(dto.getDetalle());

            pago.agregarDetalle(detalle);
        }

        Pagos pagoGuardado =
                pagosRepositorio.save(pago);

        int nuevoTotalPagado =
                totalPagado + totalNuevoPago;

        if (nuevoTotalPagado >= factura.getTotal()) {
            factura.setEstado("PAGADO");


            List<Detalle_Factura> detallesFactura =
                    detalleVentaRepositorio.buscarPorFactura(facturaId);

            for (Detalle_Factura detalle : detallesFactura) {
                if (detalle.getProducto() == null) {
                    throw new RuntimeException("El detalle de la factura no tiene producto");
                }
                movimientoStockServicio.registrarMovimiento(detalle.getProducto().getId(), "SALIDA", detalle.getCantidad(), "Venta factura #" + facturaId);
            }

        } else {

            factura.setEstado("PENDIENTE");
        }
        facturaVentaRepositorio.save(factura);
        return pagoGuardado;

    }



    @Override
    public List<Pagos> listarPagosPorFactura(Long facturaId) {
        return pagosRepositorio.listarPorFactura(facturaId);
    }

    @Override
    public Integer obtenerTotalPagado(Long facturaId) {
        return pagosRepositorio.sumarPagosPorFactura(facturaId);
    }
}

