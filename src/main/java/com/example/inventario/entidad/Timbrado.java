package com.example.inventario.entidad;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "timbrado")

public class Timbrado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    @Column(name = "nro_timbrado", nullable = false, length = 10)
    private Integer nro_timbrado;

    @Column(name = "cod_establecimiento", nullable = false, length = 20)
    private Integer cod_establecimiento;

    @Column(name = "expedicion", nullable = false, length = 20)
    private Integer expedicion;

    @Column(name = "inicio_vigencia", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inicio_vigencia;

    @Column(name = "fin_vigencia", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fin_vigencia;

    @Column(name = "desde", nullable = false, length = 6)
    private Integer desde;

    @Column(name = "hasta", nullable = false, length = 6)
    private Integer hasta;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    public Timbrado(Long id, Integer nro_timbrado, Integer cod_establecimiento, Integer expedicion, LocalDate inicio_vigencia, LocalDate fin_vigencia, Integer desde, Integer hasta, String estado) {
        this.id = id;
        this.nro_timbrado = nro_timbrado;
        this.cod_establecimiento = cod_establecimiento;
        this.expedicion = expedicion;
        this.inicio_vigencia = inicio_vigencia;
        this.fin_vigencia = fin_vigencia;
        this.desde = desde;
        this.hasta = hasta;
        this.estado = estado;
    }

    public Timbrado(Integer nro_timbrado, Integer cod_establecimiento, Integer expedicion, LocalDate inicio_vigencia, LocalDate fin_vigencia, Integer desde, Integer hasta, String estado) {
        this.nro_timbrado = nro_timbrado;
        this.cod_establecimiento = cod_establecimiento;
        this.expedicion = expedicion;
        this.inicio_vigencia = inicio_vigencia;
        this.fin_vigencia = fin_vigencia;
        this.desde = desde;
        this.hasta = hasta;
        this.estado = estado;
    }

    public Timbrado() {
    }

    public Long getId() {
        return id;
    }

    public Integer getNro_timbrado() {
        return nro_timbrado;
    }

    public Integer getCod_establecimiento() {
        return cod_establecimiento;
    }

    public Integer getExpedicion() {
        return expedicion;
    }

    public LocalDate getInicio_vigencia() {
        return inicio_vigencia;
    }

    public LocalDate getFin_vigencia() {
        return fin_vigencia;
    }

    public Integer getDesde() {
        return desde;
    }

    public Integer getHasta() {
        return hasta;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNro_timbrado(Integer nro_timbrado) {
        this.nro_timbrado = nro_timbrado;
    }

    public void setCod_establecimiento(Integer cod_establecimiento) {
        this.cod_establecimiento = cod_establecimiento;
    }

    public void setExpedicion(Integer expedicion) {
        this.expedicion = expedicion;
    }

    public void setInicio_vigencia(LocalDate inicio_vigencia) {
        this.inicio_vigencia = inicio_vigencia;
    }

    public void setFin_vigencia(LocalDate fin_vigencia) {
        this.fin_vigencia = fin_vigencia;
    }

    public void setDesde(Integer desde) {
        this.desde = desde;
    }

    public void setHasta(Integer hasta) {
        this.hasta = hasta;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Timbrado{" +
                "id=" + id +
                ", nro_timbrado=" + nro_timbrado +
                ", cod_establecimiento=" + cod_establecimiento +
                ", expedicion=" + expedicion +
                ", inicio_vigencia=" + inicio_vigencia +
                ", fin_vigencia=" + fin_vigencia +
                ", desde=" + desde +
                ", hasta=" + hasta +
                ", estado='" + estado + '\'' +
                '}';
    }
}
