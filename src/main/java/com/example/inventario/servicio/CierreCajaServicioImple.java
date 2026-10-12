package com.example.inventario.servicio;

import com.example.inventario.DTO.ResumenCierreCajaDTO;
import com.example.inventario.entidad.Cierre_caja;
import com.example.inventario.entidad.Detalle_Pago;
import com.example.inventario.entidad.apertura_caja;
import com.example.inventario.repositorio.CierreCajaRepositorio;
import com.example.inventario.repositorio.DetallePagoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CierreCajaServicioImple implements CierreCajaServicio {

    @Autowired
    private DetallePagoRepositorio detallePagoRepositorio;

    @Autowired
    private CierreCajaRepositorio cierreCajaRepositorio;

    @Autowired
    private AperturaCajaServicio aperturaCajaServicio;

    @Override
    public ResumenCierreCajaDTO obtenerResumenCierre() {
        apertura_caja apertura =
                aperturaCajaServicio.obtenerAperturaAbierta();

        List<Detalle_Pago> detalles =
                detallePagoRepositorio.buscarPorAperturaCaja(
                        apertura.getId()
                );


        double efectivo = 0;
        double tarjeta = 0;
        double cheque = 0;
        double transferencia = 0;


        for (Detalle_Pago detalle : detalles) {

            if (detalle.getTipo_pago() == null) {
                continue;
            }

            String tipo =
                    detalle.getTipo_pago()
                            .getDescripcion()
                            .trim()
                            .toUpperCase();

            double monto = detalle.getMonto();


            if (tipo.equals("EFECTIVO")) {

                efectivo += monto;

            } else if (tipo.equals("TARJETA")) {

                tarjeta += monto;

            } else if (tipo.equals("CHEQUE")) {

                cheque += monto;

            } else if (tipo.equals("TRANSFERENCIA")) {

                transferencia += monto;
            }
        }


        double totalSistema =
                efectivo
                        + tarjeta
                        + cheque
                        + transferencia;


        /*
         * Dinero que debería existir físicamente
         * en la caja.
         *
         * Monto de apertura + efectivo recaudado.
         */
        double efectivoEsperado =
                apertura.getMonto_apertura()
                        + efectivo;


        ResumenCierreCajaDTO resumen =
                new ResumenCierreCajaDTO();

        resumen.setTotalEfectivo(efectivo);
        resumen.setTotalTarjeta(tarjeta);
        resumen.setTotalCheque(cheque);
        resumen.setTotalTransferencia(transferencia);
        resumen.setTotalSistema(totalSistema);
        resumen.setEfectivoEsperado(efectivoEsperado);


        return resumen;
    }

    @Override
    @Transactional
    public void cerrarCaja(ResumenCierreCajaDTO resumenCierreCajaDTO) {
        apertura_caja apertura =
                aperturaCajaServicio.obtenerAperturaAbierta();


        if (apertura == null) {
            throw new RuntimeException(
                    "No existe una caja abierta."
            );
        }


        Cierre_caja cierre =
                new Cierre_caja();


        cierre.setAperturaCaja(apertura);

        /*
         * El empleado que abrió la caja
         * queda registrado como quien realiza
         * el cierre.
         */
        cierre.setEmpleado(
                apertura.getEmpleado()
        );


   //     ResumenCierreCajaDTO datos = null;
        cierre.setTotal_efectivo(
                resumenCierreCajaDTO.getTotalEfectivo()
        );

        cierre.setTotal_tarjeta(
                resumenCierreCajaDTO.getTotalTarjeta()
        );

        cierre.setTotal_cheque(
                resumenCierreCajaDTO.getTotalCheque()
        );

        cierre.setTotal_transferencia(
                resumenCierreCajaDTO.getTotalTransferencia()
        );

        cierre.setTotal_sistema(
                resumenCierreCajaDTO.getTotalSistema()
        );

        cierre.setEfectivo_esperado(
                resumenCierreCajaDTO.getEfectivoEsperado()
        );

        cierre.setEfectivo_contado(
                resumenCierreCajaDTO.getEfectivoContado()
        );

        double diferencia =
                resumenCierreCajaDTO.getEfectivoContado()
                        - resumenCierreCajaDTO.getEfectivoEsperado();

        cierre.setDiferencia(diferencia);

        cierre.setObservacion(
                resumenCierreCajaDTO.getObservacion()
        );
        /*
         * La diferencia la calculamos nuevamente
         * en el servidor.
         *
         * No confiamos solamente en el valor
         * enviado por JavaScript.
         */
        cierreCajaRepositorio.save(cierre);


        /*
         * Finalmente cerramos la apertura.
         */
        apertura.setAbierta(false);

        aperturaCajaServicio.guardarApertura(
                apertura
        );
    }

    @Override
    public void guardarCierre(Cierre_caja cierre) {

        cierreCajaRepositorio.save(cierre);
    }
}
