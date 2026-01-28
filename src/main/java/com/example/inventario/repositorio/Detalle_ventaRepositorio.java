package com.example.inventario.repositorio;

import com.example.inventario.entidad.Detalle_Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Detalle_ventaRepositorio extends JpaRepository <Detalle_Factura, Long> {



}
