package com.example.inventario.repositorio;
import com.example.inventario.entidad.Detalle_Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePagoRepositorio
        extends JpaRepository<Detalle_Pago, Long> {

    @Query("SELECT d FROM Detalle_Pago d WHERE d.pago.id_pago = :pagoId")
    List<Detalle_Pago> buscarPorPagoId(@Param("pagoId") Long pagoId);
}

