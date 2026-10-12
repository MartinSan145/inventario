package com.example.inventario.servicio;

import com.example.inventario.entidad.caja;
import com.example.inventario.repositorio.CajaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CajaServicioImple implements CajaServicio {

    @Autowired
    private CajaRepositorio cajaRepositorio;

    @Override
    public List<caja> listarAperturasCajas() {
        return  cajaRepositorio.findAll();
    }

    @Override
    public caja obtenerCajaPorId(Long id) {
        return cajaRepositorio.findById(id).orElseThrow(() ->
                new RuntimeException("No se encontró la caja con ID: \" + id"));
    }

    @Override
    public caja guardarAperturaC(caja Caja) {
        return null;
    }
}
