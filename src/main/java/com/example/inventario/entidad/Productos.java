package com.example.inventario.entidad;


import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name = "productos")

public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    @Column(name = "precio_venta", nullable = false, length = 50)
    private Integer precio_venta;

    @Column(name = "precio_compra", nullable = false, length = 50)
    private Integer precio_compra;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    public Productos(Long id, String descripcion, Integer precio_venta, Integer precio_compra, Integer stock, Categoria categoria) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio_venta = precio_venta;
        this.precio_compra = precio_compra;
        this.stock = stock;
        this.categoria = categoria;
    }

    public Productos() {
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

    public Integer getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Integer precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Integer getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(Integer precio_compra) {
        this.precio_compra = precio_compra;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", precio_venta=" + precio_venta +
                ", precio_compra=" + precio_compra +
                ", stock=" + stock +
                ", categoria=" + categoria +
                '}';
    }
}


