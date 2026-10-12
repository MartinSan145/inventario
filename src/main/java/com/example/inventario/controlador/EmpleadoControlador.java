package com.example.inventario.controlador;


import com.example.inventario.entidad.empleado;
import com.example.inventario.servicio.CiudadServicio;
import com.example.inventario.servicio.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @GetMapping({"/empleados"})
    public String listarEmpleados(Model modelo) {
        modelo.addAttribute("empleados", empleadoServicio.listarTodoslosEmpleados());
        return "empleados";
    }
    @Autowired
    private CiudadServicio ciudadServicio;
    @GetMapping({"/empleados/nuevo"})
    public String crearEmpleado(Model modelo) {
        modelo.addAttribute("empleado", new empleado());
        modelo.addAttribute("ciudad", ciudadServicio.listarCiudad());
        return "crear_empleados";
    }

    @PostMapping("/empleado")
    private String guardarEmpleado(@ModelAttribute("empleado") empleado empleados) {
        empleadoServicio.guardarEmpleados(empleados);
        return "redirect:/empleados";
    }

}
