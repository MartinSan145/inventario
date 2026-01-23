package com.example.inventario.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "vista_productos")
@Immutable
public class vista_productos {

    @Id
    private Long id;

    private String descripcion;
    private Integer precio_venta;
    private  Integer precio_compra;
    private  Integer stock;
    private  String categoria;

}
