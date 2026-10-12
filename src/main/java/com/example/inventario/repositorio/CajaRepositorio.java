package com.example.inventario.repositorio;

import com.example.inventario.entidad.caja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CajaRepositorio extends JpaRepository <caja, Long> {

}
