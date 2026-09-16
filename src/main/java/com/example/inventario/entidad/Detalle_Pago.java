package com.example.inventario.entidad;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

    @Entity
    @Table(name = "detalle_pago")
    public class Detalle_Pago {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_detalle_pago;

        @ManyToOne
        @JoinColumn(name = "id_pago", nullable = false)
        @JsonIgnore
        private Pagos pago;

        @ManyToOne
        @JoinColumn(name = "id_tipo_pago", nullable = false)
        private Tipo_pago tipo_pago;

        @Column(name = "monto", nullable = false)
        private Integer monto;

        @Column(name = "detalle", length = 100)
        private String detalle;

        public Detalle_Pago() {
        }

        public Detalle_Pago(
                Pagos pago,
                Tipo_pago tipo_pago,
                Integer monto) {

            this.pago = pago;
            this.tipo_pago = tipo_pago;
            this.monto = monto;
        }

        public Long getId_detalle_pago() {
            return id_detalle_pago;
        }

        public void setId_detalle_pago(Long id_detalle_pago) {
            this.id_detalle_pago = id_detalle_pago;
        }

        public Pagos getPago() {
            return pago;
        }

        public void setPago(Pagos pago) {
            this.pago = pago;
        }

        public Tipo_pago getTipo_pago() {
            return tipo_pago;
        }

        public void setTipo_pago(Tipo_pago tipo_pago) {
            this.tipo_pago = tipo_pago;
        }

        public Integer getMonto() {
            return monto;
        }

        public void setMonto(Integer monto) {
            this.monto = monto;
        }

        public void setDetalle(String detalle) {
            this.detalle = detalle;
        }

        public String getDetalle() {
            return detalle;
        }

        @Override
        public String toString() {
            return "Detalle_Pago{" +
                    "id_detalle_pago=" + id_detalle_pago +
                    ", pago=" + pago +
                    ", tipo_pago=" + tipo_pago +
                    ", monto=" + monto +
                    ", detalle='" + detalle + '\'' +
                    '}';
        }

    }

