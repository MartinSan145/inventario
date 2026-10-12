package com.example.inventario.entidad;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "apertura_caja")

public class apertura_caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_caja")
    private caja caja;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private empleado empleado;

    @Column(name = "fecha", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate fecha;

    @Column(name = "monto_apertura", nullable = false)
    private Integer monto_apertura;

    @Column(name = "observacion", length = 50)
    private String observacion;

    @Column(name = "cotizacion", nullable = false)
    private Double cotizacion;

    @Column(name = "abierta", nullable = false)
    private Boolean abierta;

    public apertura_caja(Long id, caja caja, LocalDate fecha, Integer monto_apertura, String observacion, Double cotizacion, Boolean abierta, empleado empleado) {
        this.id = id;
        this.caja = caja;
        this.fecha = fecha;
        this.monto_apertura = monto_apertura;
        this.observacion = observacion;
        this.cotizacion = cotizacion;
        this.abierta = abierta;
        this.empleado = empleado;
    }

    public apertura_caja() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public caja getCaja() {
        return caja;
    }

    public void setCaja(caja caja) {
        this.caja = caja;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getMonto_apertura() {
        return monto_apertura;
    }

    public void setMonto_apertura(Integer monto_apertura) {
        this.monto_apertura = monto_apertura;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getAbierta() {
        return abierta;
    }

    public void setAbierta(Boolean abierta) {
        this.abierta = abierta;
    }

    public Double getCotizacion() {
        return cotizacion;
    }

    public empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(empleado empleado) {
        this.empleado = empleado;
    }

    public void setCotizacion(Double cotizacion) {
        this.cotizacion = cotizacion;
    }

    @Override
    public String toString() {
        return "apertura_caja{" +
                "id=" + id +
                ", caja=" + caja +
                ", fecha=" + fecha +
                ", monto_apertura=" + monto_apertura +
                ", observacion='" + observacion + '\'' +
                ", cotizacion=" + cotizacion +
                ", abierta=" + abierta +
                '}';
    }

    public boolean isAbierta() {
        return abierta;
    }
    public void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }
}
