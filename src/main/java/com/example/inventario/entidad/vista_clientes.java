package com.example.inventario.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "vista_clientes")
@Immutable
public class vista_clientes {

    @Id
    private Long id;

    private String nombre;
    private String apellido;
    private Integer ruc;
    private Integer telefono;
    private String ciudadd;
    private String email;

    public vista_clientes() {
    }
}
