package com.example.inventario.controlador;

import com.example.inventario.entidad.Clientes;
import com.example.inventario.entidad.Factura_venta;
import com.example.inventario.entidad.Timbrado;
import com.example.inventario.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.example.inventario.DTO.DetalleFacturaDTO;
import java.util.List;
import java.util.ArrayList;

@Controller

public class FacturaVentaControlador {

    @Autowired
    private Factura_ventaServicio facturaVentaServicio;
    private ClienteServicio clienteServicio;
    private ProductoServicio productoServicio;
    private TimbradoServicio timbradoServicio;
    private TipoPagoServicio tipoPagoServicio;


    public FacturaVentaControlador(ClienteServicio clienteServicio, Factura_ventaServicio facturaVentaServicio, ProductoServicio productoServicio, TimbradoServicio timbradoservicio, TipoPagoServicio tipoPagoServicio) {
        this.clienteServicio = clienteServicio;
        this.facturaVentaServicio = facturaVentaServicio;
        this.productoServicio = productoServicio;
        this.timbradoServicio = timbradoservicio;
        this.tipoPagoServicio = tipoPagoServicio;
    }

    @GetMapping({"/factura_venta", "/"})
    public String listarfacturas(Model modelo) {
        modelo.addAttribute("factura", new Factura_venta());
        modelo.addAttribute("cliente", clienteServicio.listarTodoslosClientes());
        modelo.addAttribute("producto", productoServicio.listarTodoslosProductos());
        modelo.addAttribute("timbrado", timbradoServicio.listarTodoslosTimbrado());

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy' 'HH:mm");
        modelo.addAttribute("fecha", LocalDateTime.now().format(formato));

        return "factura_venta";
    }

    @GetMapping("/admin_facturas")
    public String mostrar_adminFacturas (Model modelo) {
        modelo.addAttribute("facturasPendientes", facturaVentaServicio.listarFacturas());
        return "admin_facturas";
    }



    @GetMapping({"/facturas", "/"})
    public String mostrarFacturasPendientes(Model modelo) {
        modelo.addAttribute("facturasPendientes", facturaVentaServicio.listarFacturasPendientes());
        modelo.addAttribute("tipo_pago", tipoPagoServicio.listarTipoPago());
        return "facturas";
    }

    @PostMapping("/facturas/{id}/anular")
    public String anularFactura(@PathVariable Long id) {
        facturaVentaServicio.anularFactura(id);
        return "redirect:/facturas";
    }

    @GetMapping("/imprimir/{id}")
    public String imprimirFactura(@PathVariable Long id, Model model) {

        Factura_venta factura = facturaVentaServicio.obtenerTotalPorId(id);

        model.addAttribute("factura", factura);

        return "imprimir";
    }
    @GetMapping("/ticket/{id}")
    public String imprimirTicket(@PathVariable Long id, Model model) {

        Factura_venta factura = facturaVentaServicio.obtenerTotalPorId(id);

        model.addAttribute("factura", factura);

        return "ticket";
    }

    @GetMapping({"/facturas/{id}/datos-pago"})
    @ResponseBody
    public Integer obtenerTotal(@PathVariable Long id) {
        return facturaVentaServicio.obtenerTotalPorId(id).getTotal();
    }

    @GetMapping("/productos/{id}/precio")
    @ResponseBody
    public Integer obtenerPrecio(@PathVariable Long id) {
        return productoServicio.obtenerProductosPorId(id).getPrecio_venta();
    }

    @GetMapping("/cliente/{id}/ruc")
    @ResponseBody
    public Integer obtenerRuc(@PathVariable Long id) {
        return clienteServicio.obtenerClientesPorId(id).getRuc();
    }

    @GetMapping("/cliente/{id}")
    @ResponseBody
    public Clientes obtenerCliente(@PathVariable Long id) {
        return clienteServicio.obtenerClientesPorId(id);
    }

    @PostMapping("/factura_venta")
    public String guardarFactura(
            @ModelAttribute("factura") Factura_venta factura,
            @RequestParam("timbrado") Long timbradoId,
            @RequestParam("cliente") Long clienteId,
            jakarta.servlet.http.HttpServletRequest request) {

        Timbrado timbrado = timbradoServicio.buscarPorId(timbradoId);

        Clientes cliente = clienteServicio.obtenerClientesPorId(clienteId);

        factura.setTimbrado(timbrado);
        factura.setCliente(cliente);


        List<DetalleFacturaDTO> detalles = new ArrayList<>();


        int index = 0;

        while (request.getParameter("detalles[" + index + "].productoId") != null) {

            DetalleFacturaDTO dto = new DetalleFacturaDTO();

            dto.setProductoId(
                    Long.valueOf(
                            request.getParameter(
                                    "detalles[" + index + "].productoId"
                            )
                    )
            );

            dto.setCantidad(
                    Integer.parseInt(
                            request.getParameter(
                                    "detalles[" + index + "].cantidad"
                            )
                    )
            );

            dto.setPrecio_unitario(
                    Double.parseDouble(
                            request.getParameter(
                                    "detalles[" + index + "].precio_unitario"
                            )
                    )
            );

            dto.setSubtotal(
                    Double.parseDouble(
                            request.getParameter(
                                    "detalles[" + index + "].subtotal"
                            )
                    )
            );


            detalles.add(dto);

            index++;
        }


        facturaVentaServicio.guardarFactura(
                factura,
                detalles
        );

        return "redirect:/factura_venta";
    }



}