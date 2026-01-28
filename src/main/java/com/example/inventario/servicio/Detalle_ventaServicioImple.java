package com.example.inventario.servicio;

import com.example.inventario.entidad.Detalle_Factura;
import com.example.inventario.repositorio.Detalle_ventaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Detalle_ventaServicioImple implements Detalle_ventaServicio{

   @Autowired
   private Detalle_ventaRepositorio detalleVentaRepositorio;

    @Override
    public List<Detalle_Factura> listarDetalle() {
        return detalleVentaRepositorio.findAll();
    }
}
