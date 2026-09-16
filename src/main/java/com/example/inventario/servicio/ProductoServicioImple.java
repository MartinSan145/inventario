package com.example.inventario.servicio;

import com.example.inventario.entidad.Productos;
import com.example.inventario.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicioImple implements ProductoServicio{

@Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<Productos> listarTodoslosProductos() {
        return productoRepositorio.findAll();
    }

    @Override
    public Productos guardarProductos(Productos productos) {
        return productoRepositorio.save(productos);
    }

    @Override
    public Productos obtenerProductosPorId(Long id) {

        return productoRepositorio.findById(id).get();
    }

    @Override
    public Productos actualizarProductos(Productos productos) {

        return productoRepositorio.save(productos);
    }

    @Override
    public void eliminarProductos(Long id) {
        productoRepositorio.deleteById(id);
    }
}
