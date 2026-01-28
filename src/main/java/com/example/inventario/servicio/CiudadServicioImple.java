package com.example.inventario.servicio;

import com.example.inventario.entidad.Ciudad;
import com.example.inventario.repositorio.CiudadRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CiudadServicioImple implements CiudadServicio{

    @Autowired
    private CiudadRepositorio ciudadRepositorio;

    @Override
    public List<Ciudad> listarCiudad() {
        return ciudadRepositorio.findAll();
    }
}
