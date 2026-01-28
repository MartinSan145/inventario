package com.example.inventario.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 20)
    private String apellido;

    @Column(name = "ruc", nullable = false, length = 20)
    private Integer ruc;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "telefono", nullable = false, length = 20, unique = true)
    private Integer telefono;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", nullable = false)
    private Ciudad ciudad;

    public Clientes(Long id, String nombre, String apellido, Integer ruc, String email, Integer telefono, Ciudad ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ruc = ruc;
        this.email = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    public Clientes(String nombre, String apellido, Integer ruc, String email, Integer telefono, Ciudad ciudad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ruc = ruc;
        this.email = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    public Clientes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getRuc() {
        return ruc;
    }

    public void setRuc(Integer ruc) {
        this.ruc = ruc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", ruc=" + ruc +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", ciudad=" + ciudad +
                '}';
    }
}