package com.example.inventario.controlador;

import com.example.inventario.entidad.Clientes;
import com.example.inventario.servicio.CiudadServicio;
import com.example.inventario.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteControlador {

    @Autowired
    private ClienteServicio serviciocliente;

    @GetMapping({"/clientes"})
    public String listarClientes(Model modelo) {
        modelo.addAttribute("clientes", serviciocliente.listarTodoslosClientes());
        return "clientes";
    }
    @Autowired
    private CiudadServicio ciudadServicio;
    @GetMapping({"clientes/nuevo"})
    public String crearCliente(Model modelo) {
       // modelo.addAttribute("cliente", new Cliente());
        modelo.addAttribute("clientes", new Clientes());
        modelo.addAttribute("ciudad", ciudadServicio.listarCiudad());

       // modelo.addAttribute("ciudad"), ciudadServicio.listarCiudad();
        return "crear_clientes";
    }
    @PostMapping("/clientes")
    public String guardarCliente(@ModelAttribute("cliente") Clientes clientes) {
        serviciocliente.guardarClientes(clientes);
        return "redirect:/clientes";
    }
    @GetMapping("/clientes/editar/{id}")
    public String mostrarformularioeditar(@PathVariable("id") Long id, Model modelo) {
        modelo.addAttribute("cliente", serviciocliente.obtenerClientesPorId(id));
        modelo.addAttribute("ciudad", ciudadServicio.listarCiudad());
        return "editar_clientes";
    }
    @PostMapping("/clientes/{id}")
    public String actualizarClientes(@PathVariable("id") Long id, @ModelAttribute("cliente") Clientes clientes, Model modelo){
        Clientes clienteExistente = serviciocliente.obtenerClientesPorId(id);
        clienteExistente.setId(id);
        clienteExistente.setNombre(clientes.getNombre());
        clienteExistente.setApellido(clientes.getApellido());
        clienteExistente.setRuc(clientes.getRuc());
        clienteExistente.setTelefono(clientes.getTelefono());
        clienteExistente.setEmail(clientes.getEmail());
        clienteExistente.setCiudad(clientes.getCiudad());

        serviciocliente.actualizarClientes(clienteExistente);
        return ("redirect:/clientes");
    }

    @GetMapping("/clientes/{id}")
    public String eliminarClientes(@PathVariable Long id){
        serviciocliente.eliminarClientes(id);
        return "redirect:/clientes";
    }



}
