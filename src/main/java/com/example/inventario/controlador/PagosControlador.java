package com.example.inventario.controlador;

import com.example.inventario.DTO.PagoDTO;
import com.example.inventario.entidad.Clientes;
import com.example.inventario.entidad.Pagos;
import com.example.inventario.entidad.Tipo_pago;
import com.example.inventario.servicio.PagosServicio;
import com.example.inventario.servicio.TipoPagoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PagosControlador {

    @Autowired
    private PagosServicio pagosServicio;
    @Autowired
    private TipoPagoServicio tipoPagoServicio;

    @PostMapping("/guardar-pago")
    @ResponseBody
    public ResponseEntity<?> guardarPago(
            @RequestBody PagoDTO pagoDTO) {

        try {

            Pagos pago =
                    pagosServicio.guardarPago(
                            pagoDTO.getFacturaId(),
                            pagoDTO.getDetalles());

            return ResponseEntity.ok(pago);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

    }
}
