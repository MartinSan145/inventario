package com.example.inventario.DTO;

public class ResumenCierreCajaDTO {

    private double totalEfectivo;
    private double totalTarjeta;
    private double totalCheque;
    private double totalTransferencia;
    private double totalSistema;
    private double efectivoEsperado;
    private double efectivoContado;
    private String observacion;


    public ResumenCierreCajaDTO() {
    }

    public ResumenCierreCajaDTO(double totalEfectivo, double totalTarjeta, double totalCheque, double totalTransferencia, double totalSistema, double efectivoEsperado) {
        this.totalEfectivo = totalEfectivo;
        this.totalTarjeta = totalTarjeta;
        this.totalCheque = totalCheque;
        this.totalTransferencia = totalTransferencia;
        this.totalSistema = totalSistema;
        this.efectivoEsperado = efectivoEsperado;
    }

    public double getTotalEfectivo() {
        return totalEfectivo;
    }

    public void setTotalEfectivo(double totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    public double getTotalTarjeta() {
        return totalTarjeta;
    }

    public void setTotalTarjeta(double totalTarjeta) {
        this.totalTarjeta = totalTarjeta;
    }

    public double getTotalCheque() {
        return totalCheque;
    }

    public void setTotalCheque(double totalCheque) {
        this.totalCheque = totalCheque;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getTotalTransferencia() {
        return totalTransferencia;
    }

    public void setTotalTransferencia(double totalTransferencia) {
        this.totalTransferencia = totalTransferencia;
    }

    public double getTotalSistema() {
        return totalSistema;
    }

    public void setTotalSistema(double totalSistema) {
        this.totalSistema = totalSistema;
    }

    public double getEfectivoEsperado() {
        return efectivoEsperado;
    }

    public void setEfectivoEsperado(double efectivoEsperado) {
        this.efectivoEsperado = efectivoEsperado;
    }

    public double getEfectivoContado() {
        return efectivoContado;
    }

    public void setEfectivoContado(double efectivoContado) {
        this.efectivoContado = efectivoContado;
    }



    @Override
    public String toString() {
        return "ResumenCierreCajaDTO{" +
                "totalEfectivo=" + totalEfectivo +
                ", totalTarjeta=" + totalTarjeta +
                ", totalCheque=" + totalCheque +
                ", totalTransferencia=" + totalTransferencia +
                ", totalSistema=" + totalSistema +
                ", efectivoEsperado=" + efectivoEsperado +
                '}';
    }


    public String getObservacion() {
        return observacion;
    }


}