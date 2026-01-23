package com.example.inventario.repositorio;

//import com.example.inventario.entidad.Productos;
import com.example.inventario.entidad.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepositorio extends JpaRepository <Productos, Long> {



}
