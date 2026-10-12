package com.example.inventario.servicio;

import com.example.inventario.entidad.apertura_caja;
import com.example.inventario.repositorio.AperturaCajaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AperturaCajaServicioImple implements AperturaCajaServicio{

    @Autowired
    private AperturaCajaRepositorio aperturaCajaRepositorio;

    @Override
    public List<apertura_caja> listarApertura() {
        return aperturaCajaRepositorio.findAll();
    }

    @Override
    public apertura_caja obternerAperturaPorId(Long id) {
        return aperturaCajaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró la apertura de caja con ID: " + id
                ));
    }

    @Override
    public apertura_caja obtenerAperturaAbierta() {
        return aperturaCajaRepositorio.findByAbiertaTrue()
                .orElseThrow(() ->
                        new RuntimeException("No existe una caja abierta"));
    }

    @Override
    public boolean existeCajaAbierta() {
        return aperturaCajaRepositorio.findByAbiertaTrue().isPresent();
    }

    @Override
    public apertura_caja guardarApertura(apertura_caja aperturaCaja) {
        return aperturaCajaRepositorio.save(aperturaCaja);
    }

    @Override
    public void cerrarCaja() {
        apertura_caja aperturaCaja =
                aperturaCajaRepositorio.findByAbiertaTrue()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "No existe una caja abierta para cerrar"
                                )
                        );

        aperturaCaja.setAbierta(false);

        aperturaCajaRepositorio.save(aperturaCaja);
    }
}

