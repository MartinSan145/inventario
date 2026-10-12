package com.example.inventario.servicio;

import com.example.inventario.DTO.ResumenCierreCajaDTO;
import com.example.inventario.entidad.Cierre_caja;

public interface CierreCajaServicio {

    ResumenCierreCajaDTO obtenerResumenCierre();

    void cerrarCaja(ResumenCierreCajaDTO resumenCierreCajaDTO);

    void guardarCierre(Cierre_caja cierre);
}
