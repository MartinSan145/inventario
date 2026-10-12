package com.example.inventario.repositorio;

import com.example.inventario.entidad.Cierre_caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CierreCajaRepositorio extends JpaRepository<Cierre_caja, Long> {



}
