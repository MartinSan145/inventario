package com.example.inventario.servicio;


import com.example.inventario.entidad.Categoria;
import com.example.inventario.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicioImple implements CategoriaServicio{

    @Autowired
    private CategoriaRepositorio repositorio;

    @Override
    public List<Categoria> listarCategoria() {
        return repositorio.findAll();

    }
}
