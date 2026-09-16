package com.example.inventario.controlador;

import com.example.inventario.entidad.Timbrado;
import com.example.inventario.servicio.TimbradoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TimbradoControlador {

    @Autowired
    private TimbradoServicio timbradoServicio;

    @GetMapping("/timbrado")
    public String listar_timbrado(Model modelo) {
        modelo.addAttribute("timbrado", timbradoServicio.listarTodoslosTimbrado()
        );
        return "timbrado";
    }

    @GetMapping("/timbrado/nuevo")
    public String mostrarFormularioNuevo(Model modelo) {
        modelo.addAttribute("timbrado", new Timbrado());
        return "crear_timbrado";
    }

    @PostMapping("/timbrado")
    public String guardarTimbrado(@ModelAttribute("timbrado") Timbrado timbrado) {
        timbradoServicio.guardarTimbrado(timbrado);
        return "redirect:/timbrado";
    }

    @GetMapping("/timbrado/editar/{id}")
    public String mostrarformeditar(@PathVariable("id") Long id, Model modelo) {
        modelo.addAttribute("timbrado", timbradoServicio.obtenerTimbradoPorId(id));
        return "editar_timbrado";
    }
    @PostMapping("/timbrado/{id}")
    public String actualizarTimbrado(@PathVariable("id") Long id, @ModelAttribute("timbrado") Timbrado timbrado, Model modelo) {
        Timbrado timbradoExistente = timbradoServicio.obtenerTimbradoPorId(id);
        timbradoExistente.setId(id);
        timbradoExistente.setCod_establecimiento(timbrado.getCod_establecimiento());
        timbradoExistente.setExpedicion(timbrado.getExpedicion());
        timbradoExistente.setInicio_vigencia(timbrado.getInicio_vigencia());
        timbradoExistente.setFin_vigencia(timbrado.getFin_vigencia());
        timbradoExistente.setDesde(timbrado.getDesde());
        timbradoExistente.setHasta(timbrado.getHasta());
        timbradoExistente.setEstado(timbrado.getEstado());

        timbradoServicio.actualizarTimbrado(timbradoExistente);
        return ("redirect:/timbrado");
    }
    @GetMapping("/timbrado/{id}")
    public String eliminarTimbrado(@PathVariable Long id){
        timbradoServicio.eliminarTimbrado(id);
        return "redirect:/timbrado";
    }
}
