package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EstadisticasGoleadorDTO {
    private long idEstadistica;
    private long idJugador;
    private String nombreJugador;
    private String equipo;
    private long cantidadGoles;
    private long idEquipo;

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public long getIdEstadistica() {
        return idEstadistica;
    }

    public void setIdEstadistica(long idEstadistica) {
        this.idEstadistica = idEstadistica;
    }

    public long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(long idJugador) {
        this.idJugador = idJugador;
    }

    public long getCantidadGoles() {
        return cantidadGoles;
    }

    public void setCantidadGoles(long cantidadGoles) {
        this.cantidadGoles = cantidadGoles;
    }

    public long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(long idEquipo) {
        this.idEquipo = idEquipo;
    }
}
