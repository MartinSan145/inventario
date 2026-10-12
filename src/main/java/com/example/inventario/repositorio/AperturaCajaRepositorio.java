package com.example.inventario.repositorio;

import com.example.inventario.entidad.apertura_caja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AperturaCajaRepositorio extends JpaRepository <apertura_caja, Long> {

    Optional<apertura_caja> findByAbiertaTrue();

    boolean existsByAbiertaTrue();

}
