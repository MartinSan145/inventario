package com.example.inventario.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockControlador {

    @GetMapping("/stock")
    public String stock() {
        return "stock";
    }
    @GetMapping("/movimiento_stock")
        public String movimiento_stock() {
        return "movimiento_stock";
    }
}
