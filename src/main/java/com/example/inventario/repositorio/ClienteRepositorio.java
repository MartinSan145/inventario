package com.example.inventario.repositorio;

import com.example.inventario.entidad.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio  extends JpaRepository <Clientes, Long> {

}
