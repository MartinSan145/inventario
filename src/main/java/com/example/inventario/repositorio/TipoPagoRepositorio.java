package com.example.inventario.repositorio;


import com.example.inventario.entidad.Tipo_pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagoRepositorio extends JpaRepository <Tipo_pago, Long> {


}
