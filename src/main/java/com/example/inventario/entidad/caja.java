package com.example.inventario.entidad;


import jakarta.persistence.*;

@Entity
@Table(name = "caja")
public class caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", nullable = false, length = 30)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    public caja() {
    }

    public caja(Boolean estado, String descripcion, Long id) {
        this.estado = estado;
        this.descripcion = descripcion;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "caja{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                '}';
    }
}
