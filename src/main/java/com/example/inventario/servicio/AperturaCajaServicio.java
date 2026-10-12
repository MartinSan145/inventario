package com.example.inventario.servicio;

import com.example.inventario.entidad.apertura_caja;

import java.util.List;

public interface AperturaCajaServicio {

    List<apertura_caja> listarApertura();

    apertura_caja obternerAperturaPorId(Long id);

    apertura_caja obtenerAperturaAbierta();

    boolean existeCajaAbierta();

    apertura_caja guardarApertura(apertura_caja aperturaCaja);

    void cerrarCaja();

}
