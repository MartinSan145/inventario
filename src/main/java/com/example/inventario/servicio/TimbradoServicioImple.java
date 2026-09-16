package com.example.inventario.servicio;

import com.example.inventario.entidad.Timbrado;
import com.example.inventario.repositorio.TimbradoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimbradoServicioImple implements TimbradoServicio {

    @Autowired
    private TimbradoRepositorio timbradoRepositorio;

    @Override
    public List<Timbrado> listarTodoslosTimbrado() {
        return timbradoRepositorio.findAll();
    }
    @Override
    public Timbrado guardarTimbrado(Timbrado timbrado) {
        return timbradoRepositorio.save(timbrado);
    }
    @Override
    public Timbrado obtenerTimbradoPorId (Long id) {
        return timbradoRepositorio.findById(id).get();
    }
    @Override
    public Timbrado actualizarTimbrado (Timbrado timbrado) {
        return timbradoRepositorio.save(timbrado);
    }

    @Override
    public void eliminarTimbrado(Long id) {
        timbradoRepositorio.deleteById(id);
    }

    @Override
    public Timbrado buscarPorId(Long timbradoId) {
        return timbradoRepositorio.findById(timbradoId).orElseThrow(() ->
                new RuntimeException("Timbrado no encontrado: " + timbradoId));
    }


}
