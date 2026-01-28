package com.example.inventario.servicio;


import com.example.inventario.entidad.Factura_venta;
import com.example.inventario.repositorio.Factura_ventaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Factura_ventaServicioImple implements Factura_ventaServicio{


    @Autowired
    private Factura_ventaRepositorio facturaVentaRepositorio;

    @Override
    public List<Factura_venta> listarFacturas() {
        return facturaVentaRepositorio.findAll();
    }
}
