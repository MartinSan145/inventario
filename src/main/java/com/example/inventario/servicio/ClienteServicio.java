package com.example.inventario.servicio;

import com.example.inventario.entidad.Clientes;
import com.example.inventario.entidad.Timbrado;

import java.util.List;

public interface ClienteServicio {

    public List <Clientes> listarTodoslosClientes();

    public Clientes guardarClientes(Clientes clientes);

    public Clientes obtenerClientesPorId(Long Id);

    public Clientes actualizarClientes(Clientes clientes);

    public Timbrado buscarPorId(Long timbradoId);

    public void eliminarClientes(Long id);

}
