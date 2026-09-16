package com.example.inventario.servicio;

import com.example.inventario.entidad.Timbrado;

import java.util.List;

public interface TimbradoServicio {

    public List<Timbrado> listarTodoslosTimbrado();

    public Timbrado guardarTimbrado(Timbrado timbrado);

    public Timbrado obtenerTimbradoPorId(Long id);

    public Timbrado actualizarTimbrado(Timbrado timbrado);

    public void eliminarTimbrado(Long id);

    public Timbrado buscarPorId(Long timbradoId);
}
