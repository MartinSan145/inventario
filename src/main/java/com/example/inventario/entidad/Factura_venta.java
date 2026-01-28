package com.example.inventario.entidad;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "factura_venta")
public class Factura_venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nro_factura", nullable = false, length = 13, unique = true)
    private int nro_factura;

    @Column(name = "fecha", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes clientes;

    @OneToMany(mappedBy = "factura_venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detalle_Factura> detalle_facturas =new ArrayList<>();

    public Factura_venta(Long id, int nro_factura, LocalDateTime fecha, Clientes clientes) {
        this.id = id;
        this.nro_factura = nro_factura;
        this.fecha = fecha;
        this.clientes = clientes;
    }

    public Factura_venta() {
    }

    public void agregarDetalle(Detalle_Factura detalle_factura) {
        detalle_facturas.add(detalle_factura);
        detalle_factura.setFactura_venta(this);
    }

    public void removerDetalle(Detalle_Factura detalle_factura) {
        detalle_facturas.remove(detalle_factura);
        detalle_factura.setFactura_venta(null);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNro_factura() {
        return nro_factura;
    }

    public void setNro_factura(int nro_factura) {
        this.nro_factura = nro_factura;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Clientes getCliente() {
        return clientes;
    }

    public void setCliente(Clientes clientes) {
        this.clientes = clientes;
    }

    public List<Detalle_Factura> getDetalle_facturas() {
        return detalle_facturas;
    }

    public void setDetalle_facturas(List<Detalle_Factura> detalle_facturas) {
        this.detalle_facturas = detalle_facturas;
    }
}
