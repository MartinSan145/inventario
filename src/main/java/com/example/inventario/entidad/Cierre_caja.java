package com.example.inventario.entidad;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cierre_caja")
public class Cierre_caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_aperturaCaja")
    private apertura_caja aperturaCaja;

    @OneToOne
    @JoinColumn(name = "id_empleado")
    private empleado empleadoo;

    @Column(name = "fecha_cierre", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fecha_cierre;

    @Column(name = "total_efectivo", nullable = false)
    private double total_efectivo;

    @Column(name = "total_tarjeta")
    private double total_tarjeta;

    @Column(name = "total_cheque")
    private double total_cheque;

    @Column(name = "total_transferencia")
    private double total_transferencia;

    @Column(name = "total_sistema", nullable = false)
    private  double total_sistema;

    @Column(name = "diferencia")
    private double diferencia;

    @Column(name = "efectivo_sistema", nullable = false)
    private double efectivo_sistema;

    @Column(name = "efectivo_contado", nullable = false)
    private double efectivo_contado;

    @Column(name = "observacion", nullable = false)
    private String observacion;

    public Cierre_caja(Long id, apertura_caja aperturaCaja, empleado empleadoo, LocalDateTime fecha_cierre, double total_efectivo, double total_tarjeta, double total_cheque, double total_transferencia, double total_sistema, double diferencia, double efectivo_sistema, double efectivo_contado, String observacion) {
        this.id = id;
        this.aperturaCaja = aperturaCaja;
        this.empleadoo = empleadoo;
        this.fecha_cierre = fecha_cierre;
        this.total_efectivo = total_efectivo;
        this.total_tarjeta = total_tarjeta;
        this.total_cheque = total_cheque;
        this.total_transferencia = total_transferencia;
        this.total_sistema = total_sistema;
        this.diferencia = diferencia;
        this.efectivo_sistema = efectivo_sistema;
        this.efectivo_contado = efectivo_contado;
        this.observacion = observacion;
    }

    public Cierre_caja() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public apertura_caja getAperturaCaja() {
        return aperturaCaja;
    }

    public void setAperturaCaja(apertura_caja aperturaCaja) {
        this.aperturaCaja = aperturaCaja;
    }

    public empleado getEmpleadoo() {
        return empleadoo;
    }

    public void setEmpleadoo(empleado empleadoo) {
        this.empleadoo = empleadoo;
    }

    public LocalDateTime getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(LocalDateTime fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    public double getTotal_efectivo() {
        return total_efectivo;
    }

    public void setTotal_efectivo(double total_efectivo) {
        this.total_efectivo = total_efectivo;
    }

    public double getTotal_tarjeta() {
        return total_tarjeta;
    }

    public void setTotal_tarjeta(double total_tarjeta) {
        this.total_tarjeta = total_tarjeta;
    }

    public double getTotal_cheque() {
        return total_cheque;
    }

    public void setTotal_cheque(double total_cheque) {
        this.total_cheque = total_cheque;
    }

    public double getTotal_transferencia() {
        return total_transferencia;
    }

    public void setTotal_transferencia(double total_transferencia) {
        this.total_transferencia = total_transferencia;
    }

    public double getTotal_sistema() {
        return total_sistema;
    }

    public void setTotal_sistema(double total_sistema) {
        this.total_sistema = total_sistema;
    }

    public double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }

    public double getEfectivo_sistema() {
        return efectivo_sistema;
    }

    public void setEfectivo_sistema(double efectivo_sistema) {
        this.efectivo_sistema = efectivo_sistema;
    }

    public double getEfectivo_contado() {
        return efectivo_contado;
    }

    public void setEfectivo_contado(double efectivo_contado) {
        this.efectivo_contado = efectivo_contado;
    }

    public String getObservacion(String observacion) {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Cierre_caja{" +
                "id=" + id +
                ", aperturaCaja=" + aperturaCaja +
                ", empleadoo=" + empleadoo +
                ", fecha_cierre=" + fecha_cierre +
                ", total_efectivo=" + total_efectivo +
                ", total_tarjeta=" + total_tarjeta +
                ", total_cheque=" + total_cheque +
                ", total_transferencia=" + total_transferencia +
                ", total_sistema=" + total_sistema +
                ", diferencia=" + diferencia +
                ", efectivo_sistema=" + efectivo_sistema +
                ", efectivo_contado=" + efectivo_contado +
                ", observacion='" + observacion + '\'' +
                '}';
    }

    public void setEmpleado(empleado empleado) {
    }

    public void setEfectivo_esperado(double efectivoEsperado) {
    }
}
