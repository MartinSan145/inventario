package com.example.inventario.entidad;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;

    @Column(name = "monto", nullable = false)
    private Integer monto;

    @Column(name = "fecha_pago", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fecha_pago;

    @Column(name = "usuario", nullable = false, length = 20)
    private String usuario;

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura_venta id_factura;

    @OneToMany(
            mappedBy = "pago",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Detalle_Pago> detalles = new ArrayList<>();

    public Pagos() {
    }

    public Pagos(
            Long id_pago,
            Integer monto,
            LocalDateTime fecha_pago,
            String usuario,
            Factura_venta id_factura) {

        this.id_pago = id_pago;
        this.monto = monto;
        this.fecha_pago = fecha_pago;
        this.usuario = usuario;
        this.id_factura = id_factura;
    }

    public List<Detalle_Pago> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle_Pago> detalles) {
        this.detalles = detalles;
    }


    public void agregarDetalle(Detalle_Pago detalle) {
        detalles.add(detalle);
        detalle.setPago(this);
    }

    public void removerDetalle(Detalle_Pago detalle) {
        detalles.remove(detalle);
        detalle.setPago(null);
    }


    public Long getId_pago() {
        return id_pago;
    }

    public void setId_pago(Long id_pago) {
        this.id_pago = id_pago;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDateTime fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Factura_venta getId_factura() {
        return id_factura;
    }

    public void setId_factura(Factura_venta id_factura) {
        this.id_factura = id_factura;
    }



    @Override
    public String toString() {
        return "Pagos{" +
                "id_pago=" + id_pago +
                ", monto=" + monto +
                ", fecha_pago=" + fecha_pago +
                ", usuario='" + usuario + '\'' +
                ", id_factura=" + id_factura +
                '}';
    }
}
