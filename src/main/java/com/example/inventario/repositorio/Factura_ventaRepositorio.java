package com.example.inventario.repositorio;


import com.example.inventario.entidad.Factura_venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Factura_ventaRepositorio extends JpaRepository <Factura_venta, Long> {
}
