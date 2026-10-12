package com.example.inventario.servicio;

import com.example.inventario.entidad.caja;

import java.util.List;

public interface CajaServicio {

    public List <caja> listarAperturasCajas();

    public caja obtenerCajaPorId(Long id);

    public caja guardarAperturaC(caja Caja);

}
