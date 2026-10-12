package com.example.inventario.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_Cierrcaja")
public class Detalle_CierreCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_CierreCaja", nullable = false)
    private Cierre_caja cierreCaja;

    @Column(name = "denominacion", nullable = false, length = 30)
    private double denominacion;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "total", nullable = false)
    private double total;

}
