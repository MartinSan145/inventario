package com.example.inventario.servicio;


import com.example.inventario.entidad.Tipo_pago;
import com.example.inventario.repositorio.TipoPagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPagoServicioImple implements TipoPagoServicio{

    @Autowired
    private TipoPagoRepositorio tipoPagoRepositorio;

    @Override
    public List<Tipo_pago> listarTipoPago() {

        return tipoPagoRepositorio.findAll();
    }

}
