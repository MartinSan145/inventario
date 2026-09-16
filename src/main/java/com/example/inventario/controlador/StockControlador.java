package com.example.inventario.controlador;

import com.example.inventario.entidad.Productos;
import com.example.inventario.servicio.MovimientoStockServicio;
import com.example.inventario.servicio.ProductoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StockControlador {

    private final MovimientoStockServicio movimientoStockServicio;
    private final ProductoServicio productoServicio;

    public StockControlador(
            MovimientoStockServicio movimientoStockServicio,
            ProductoServicio productoServicio) {

        this.movimientoStockServicio = movimientoStockServicio;
        this.productoServicio = productoServicio;
    }

    @GetMapping("/movimiento_stock")
    public String movimientoStock(Model modelo) {

        List<Productos> productos = productoServicio.listarTodoslosProductos();

        modelo.addAttribute("productos", productos);
        modelo.addAttribute(
                "movimientos",
                movimientoStockServicio.listarMovimientos()
        );

        modelo.addAttribute("totalProductos", productos.size());

        modelo.addAttribute(
                "productosStockBajo",
                productos.stream()
                        .filter(p -> p.getStock() > 0 && p.getStock() <= 5)
                        .count()
        );

        modelo.addAttribute(
                "productosSinStock",
                productos.stream()
                        .filter(p -> p.getStock() == 0)
                        .count()
        );

        return "movimiento_stock";
    }

    @PostMapping("/stock/movimiento")
    public String registrarMovimiento(
            @RequestParam Long idProducto,
            @RequestParam String tipoMovimiento,
            @RequestParam Integer cantidad,
            @RequestParam(required = false) String motivo,
            RedirectAttributes redirectAttributes) {

        try {

            movimientoStockServicio.registrarMovimiento(
                    idProducto,
                    tipoMovimiento,
                    cantidad,
                    motivo
            );

            redirectAttributes.addFlashAttribute(
                    "mensaje",
                    "Movimiento registrado correctamente"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );
        }

        return "redirect:/movimiento_stock";
    }
}