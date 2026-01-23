package com.example.inventario.servicio;

import com.example.inventario.entidad.Productos;

import java.util.List;

public interface ProductoServicio {

    public List<Productos> listarTodoslosProductos();

    public Productos guardarProductos(Productos productos);

    public Productos obtenerProductosPorId(Long Id);

    public Productos actualizarProductos(Productos productos);

    public void eliminarProductos(Long id);

}
