package com.example.inventario.servicio;

import com.example.inventario.entidad.Detalle_Pago;
import com.example.inventario.repositorio.DetallePagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePagoServicioImple implements DetallePagoServicio{

    @Autowired
    private DetallePagoRepositorio detallePagoRepositorio;

    @Override
    public List<Detalle_Pago> listarDetalles() {
        return detallePagoRepositorio.findAll();
    }

    @Override
    public Detalle_Pago guardarDetalle(Detalle_Pago detalle) {
        return detallePagoRepositorio.save(detalle);
    }

    @Override
    public List<Detalle_Pago> listarPorPago(Long pagoId) {
        return detallePagoRepositorio.buscarPorPagoId(pagoId);
    }

   @Override
   public List<Detalle_Pago> buscarDetallesPorPago(Long pagoId) {
        return detallePagoRepositorio.buscarPorPagoId(pagoId);
    }
}
