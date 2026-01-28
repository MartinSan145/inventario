package com.example.inventario.servicio;


import com.example.inventario.entidad.Factura_venta;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Factura_ventaServicio {

    public List<Factura_venta> listarFacturas();

}
