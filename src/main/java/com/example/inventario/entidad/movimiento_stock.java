package com.example.inventario.entidad;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento_stock")
public class movimiento_stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_movimiento")
    private Integer idmovimiento;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Productos productos;

    @Column(name = "tipo_movimiento", nullable = false, length = 20)
    private String tipomovimiento;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "stock_anterior", nullable = false)
    private Integer stockAnterior;

    @Column(name = "stock_nuevo", nullable = false)
    private Integer stockNuevo;

    @Column(length = 255)
    private String motivo;

    @Column
    private LocalDateTime fecha;

    @PrePersist
    public void registrarFecha() {
        fecha = LocalDateTime.now();
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public String getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getStockAnterior() {
        return stockAnterior;
    }

    public void setStockAnterior(Integer stockAnterior) {
        this.stockAnterior = stockAnterior;
    }

    public Integer getStockNuevo() {
        return stockNuevo;
    }

    public void setStockNuevo(Integer stockNuevo) {
        this.stockNuevo = stockNuevo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public movimiento_stock(Integer idmovimiento, Productos productos, String tipomovimiento, Integer cantidad, Integer stockAnterior, Integer stockNuevo, String motivo, LocalDateTime fecha) {
        this.idmovimiento = idmovimiento;
        this.productos = productos;
        this.tipomovimiento = tipomovimiento;
        this.cantidad = cantidad;
        this.stockAnterior = stockAnterior;
        this.stockNuevo = stockNuevo;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "movimiento_stock{" +
                "idmovimiento=" + idmovimiento +
                ", productos=" + productos +
                ", tipomovimiento='" + tipomovimiento + '\'' +
                ", cantidad=" + cantidad +
                ", stockAnterior=" + stockAnterior +
                ", stockNuevo=" + stockNuevo +
                ", motivo='" + motivo + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
