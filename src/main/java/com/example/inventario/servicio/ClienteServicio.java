package com.example.inventario.servicio;

import com.example.inventario.entidad.Clientes;

import java.util.List;

public interface ClienteServicio {

    public List <Clientes> listarTodoslosClientes();

    public Clientes guardarClientes(Clientes clientes);

    public Clientes obtenerClientesPorId(Long Id);

    public Clientes actualizarClientes(Clientes clientes);

    public void eliminarClientes(Long id);
}
