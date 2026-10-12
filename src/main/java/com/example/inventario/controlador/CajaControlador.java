package com.example.inventario.controlador;

import com.example.inventario.DTO.ResumenCierreCajaDTO;
import com.example.inventario.entidad.apertura_caja;
import com.example.inventario.entidad.empleado;
import com.example.inventario.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.inventario.DTO.ResumenCierreCajaDTO;
import com.example.inventario.entidad.Cierre_caja;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CajaControlador {

    @Autowired
    private CajaServicio cajaServicio;
    @Autowired
    private AperturaCajaServicio aperturaCajaServicio;

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @Autowired
    private CierreCajaServicio cierreCajaServicio;


    @GetMapping("/caja")
    public String listarApertura(Model modelo) {

        modelo.addAttribute("aperturaCaja", new apertura_caja());
        modelo.addAttribute("caja", cajaServicio.listarAperturasCajas());
        modelo.addAttribute("empleado", empleadoServicio.listarTodoslosEmpleados());


        boolean cajaAbierta = aperturaCajaServicio.existeCajaAbierta();

        modelo.addAttribute("cajaAbierta", cajaAbierta);


        return "caja";
    }

    @PostMapping("/caja")
    public String guardarAperturas(
            @ModelAttribute("aperturaCaja") apertura_caja aperturaCaja) {


        if (aperturaCajaServicio.existeCajaAbierta()) {

            return "redirect:/caja";
        }
        aperturaCaja.setAbierta(true);

        aperturaCajaServicio.guardarApertura(
                aperturaCaja
        );
        return "redirect:/caja";
    }

    @PostMapping("/caja/cerrar")
    public String cerrarCaja(
            @ModelAttribute("datos") ResumenCierreCajaDTO datos) {

        cierreCajaServicio.cerrarCaja(datos);

        return "redirect:/caja";
    }

    @GetMapping("/caja/resumen-cierre")
    @ResponseBody
    public ResumenCierreCajaDTO obtenerResumenCierre() {

        return cierreCajaServicio.obtenerResumenCierre();
    }

}