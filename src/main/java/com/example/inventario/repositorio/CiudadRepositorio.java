package com.example.inventario.repositorio;

import com.example.inventario.entidad.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepositorio extends JpaRepository <Ciudad, Long> {



}
