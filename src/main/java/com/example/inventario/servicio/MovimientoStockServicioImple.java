package com.example.inventario.servicio;

import com.example.inventario.entidad.Productos;
import com.example.inventario.entidad.movimiento_stock;
import com.example.inventario.repositorio.MovimientoStock_repositorio;
import com.example.inventario.repositorio.ProductoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoStockServicioImple
        implements MovimientoStockServicio {

    private final MovimientoStock_repositorio movimientoStockRepositorio;
    private final ProductoRepositorio productoRepositorio;

    public MovimientoStockServicioImple(
            MovimientoStock_repositorio movimientoStockRepositorio,
            ProductoRepositorio productoRepositorio) {

        this.movimientoStockRepositorio = movimientoStockRepositorio;
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    @Transactional
    public void registrarMovimiento(
            Long idProducto,
            String tipoMovimiento,
            Integer cantidad,
            String motivo) {

        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero"
            );
        }

        Productos producto = productoRepositorio
                .findById(idProducto)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado")
                );

        Integer stockAnterior = producto.getStock();

        Integer stockNuevo;

        if ("ENTRADA".equalsIgnoreCase(tipoMovimiento)) {

            stockNuevo = stockAnterior + cantidad;

        } else if ("SALIDA".equalsIgnoreCase(tipoMovimiento)) {

            if (cantidad > stockAnterior) {
                throw new IllegalArgumentException(
                        "No hay suficiente stock disponible"
                );
            }

            stockNuevo = stockAnterior - cantidad;

        } else {

            throw new IllegalArgumentException(
                    "Tipo de movimiento inválido"
            );
        }

        // Actualizar stock del producto
        producto.setStock(stockNuevo);
        productoRepositorio.save(producto);

        // Crear movimiento
        movimiento_stock movimiento = new movimiento_stock();

        movimiento.setProductos(producto);
        movimiento.setTipomovimiento(
                tipoMovimiento.toUpperCase()
        );
        movimiento.setCantidad(cantidad);
        movimiento.setStockAnterior(stockAnterior);
        movimiento.setStockNuevo(stockNuevo);
        movimiento.setMotivo(motivo);

        // Guardar historial
        movimientoStockRepositorio.save(movimiento);
    }

    @Override
    public List<movimiento_stock> listarMovimientos() {
        return movimientoStockRepositorio
                .findAllByOrderByFechaDesc();
    }

    @Override
    public List<movimiento_stock> listarMovimientosProducto(
            Long idProducto) {

        return movimientoStockRepositorio
                .findByProductos_IdOrderByFechaDesc(idProducto);
    }
}

