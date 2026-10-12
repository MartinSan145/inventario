package com.example.inventario.repositorio;

import com.example.inventario.entidad.empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository <empleado, Long>{


}
