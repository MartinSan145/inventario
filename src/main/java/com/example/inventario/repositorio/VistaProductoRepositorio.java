package com.example.inventario.repositorio;

import com.example.inventario.entidad.vista_productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VistaProductoRepositorio extends JpaRepository <vista_productos, Long> {

}
