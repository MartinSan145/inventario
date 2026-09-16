package com.example.inventario.controlador;

import com.example.inventario.servicio.Factura_ventaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuControlador {

    @Autowired
    private Factura_ventaServicio facturaVentaServicio;

    @GetMapping("/menu")
    public String mostrarMenu() {
        return "menu";
    }

}
