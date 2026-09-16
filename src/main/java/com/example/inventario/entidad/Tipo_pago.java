package com.example.inventario.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_pago")
public class Tipo_pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_pago;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    public Tipo_pago(Long id_tipo_pago, String descripcion, String estado) {
        this.id_tipo_pago = id_tipo_pago;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Tipo_pago() {
    }

    public Long getId_tipo_pago() {
        return id_tipo_pago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setId_tipo_pago(Long id_tipo_pago) {
        this.id_tipo_pago = id_tipo_pago;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Tipo_pago{" +
                "id_tipo_pago=" + id_tipo_pago +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
