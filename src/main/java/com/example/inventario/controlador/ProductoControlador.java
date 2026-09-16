package com.example.inventario.controlador;

import com.example.inventario.entidad.Productos;
import com.example.inventario.repositorio.VistaProductoRepositorio;
import com.example.inventario.servicio.CategoriaServicio;
import com.example.inventario.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoServicio servicio;

    @GetMapping({"/productos","/"})
    public String listarProductos(Model modelo) {
        modelo.addAttribute("productos", servicio.listarTodoslosProductos());
        return "productos";
    }

    @Autowired
    private CategoriaServicio categoriaServicio;
    @GetMapping("/productos/nuevo")
    public  String mostra_combo(Model modelo) {
        modelo.addAttribute("producto", new Productos());
        modelo.addAttribute("categorias", categoriaServicio.listarCategoria());
        return "crear_productos";
    }
    @PostMapping("/productos")
    public String guardarProductos(@ModelAttribute("producto") Productos productos) {
        servicio.guardarProductos(productos);
        return "redirect:/productos";
    }
    @GetMapping("/productos/editar/{id}")
    public String mostrarformularioeditar(@PathVariable("id") Long id, Model modelo) {
        modelo.addAttribute("producto", servicio.obtenerProductosPorId(id));
        modelo.addAttribute("categorias", categoriaServicio.listarCategoria());
        return "editar_productos";
    }
    @PostMapping("/productos/{id}")
    public String actualizarProductos(@PathVariable("id") Long id, @ModelAttribute("producto") Productos productos, Model modelo) {
        Productos productoExistente = servicio.obtenerProductosPorId(id);
        productoExistente.setId(id);
        productoExistente.setDescripcion(productos.getDescripcion());
        productoExistente.setPrecio_venta(productos.getPrecio_venta());
        productoExistente.setPrecio_compra(productos.getPrecio_compra());
        productoExistente.setStock(productos.getStock());
        productoExistente.setCategoria(productos.getCategoria());

        servicio.actualizarProductos(productoExistente);
        return ("redirect:/productos");

    }
    @GetMapping("/productos/{id}")
    public String eliminarProductos(@PathVariable Long id){
        servicio.eliminarProductos(id);
        return "redirect:/productos";
    }
}
