package com.example.inventario.servicio;

import com.example.inventario.entidad.Clientes;
import com.example.inventario.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicioImple implements ClienteServicio{

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Clientes> listarTodoslosClientes() {

        return clienteRepositorio.findAll();
    }

    @Override
    public Clientes guardarClientes(Clientes clientes) {
        return clienteRepositorio.save(clientes);
    }

    @Override
    public Clientes obtenerClientesPorId(Long Id) {
        return clienteRepositorio.findById(Id).get();
    }

    @Override
    public Clientes actualizarClientes(Clientes clientes) {
        return clienteRepositorio.save(clientes);
    }

    @Override
    public void eliminarClientes(Long id) {
        clienteRepositorio.deleteById(id);
    }


}
