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

    @Column(name = "nro_factura", nullable = false, length = 15, unique = true)
    private String nro_factura;

    @Column(name = "fecha", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fecha;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "total", nullable = false)
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes clientes;

    @ManyToOne
    @JoinColumn(name = "id_timbrado", nullable = false)
    private Timbrado timbrado;

    @OneToMany(mappedBy = "factura_venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detalle_Factura> detalle_facturas =new ArrayList<>();

    public Factura_venta(Long id, String nro_factura, LocalDateTime fecha, Clientes clientes, Timbrado timbrado, String estado, Integer total) {
        this.id = id;
        this.nro_factura = nro_factura;
        this.fecha = fecha;
        this.clientes = clientes;
        this.timbrado = timbrado;
        this.estado = estado;
        this.total = total;
    }

    public Factura_venta() {
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
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

    public Timbrado getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(Timbrado timbrado) {
        this.timbrado = timbrado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNro_factura() {
        return nro_factura;
    }

    public void setNro_factura(String nro_factura) {
        this.nro_factura = nro_factura;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
