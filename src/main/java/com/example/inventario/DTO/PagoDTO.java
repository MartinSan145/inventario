package com.example.inventario.DTO;

import java.util.List;

public class PagoDTO {

    private Long facturaId;
    private List<DetallePagoDTO> detalles;

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public List<DetallePagoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePagoDTO> detalles) {
        this.detalles = detalles;
    }

}
