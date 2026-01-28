package com.example.inventario.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "ciudad")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "descripcion", nullable = false, length = 30)
    private String descripcion;

    public Ciudad(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
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

    public Ciudad() {
    }

    @Override
    public String toString() {
        return "ciudad{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
