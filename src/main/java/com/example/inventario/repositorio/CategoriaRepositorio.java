package com.example.inventario.repositorio;

import com.example.inventario.entidad.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository <Categoria, Long> {
}
