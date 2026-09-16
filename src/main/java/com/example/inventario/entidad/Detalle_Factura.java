package com.example.inventario.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_factura")
public class Detalle_Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura_venta factura_venta;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Productos producto;

    @Column(name = "precio_unitario", nullable = false)
    private double precio_unitario;

    @Column(name = "subtotal", nullable = false)
    private double subtotal;

    public Detalle_Factura(Long id, Factura_venta factura_venta, int cantidad, double precio_unitario, double subtotal, Productos producto) {
        this.id = id;
        this.factura_venta = factura_venta;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
        this.producto = producto;

    }

    public Detalle_Factura(Factura_venta factura_venta, int cantidad, double precio_unitario, double subtotal, Productos producto) {
        this.factura_venta = factura_venta;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    public Detalle_Factura(Productos producto) {
        this.producto = producto;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Detalle_Factura() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factura_venta getFactura_venta() {
        return factura_venta;
    }

    public void setFactura_venta(Factura_venta factura_venta) {
        this.factura_venta = factura_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}

