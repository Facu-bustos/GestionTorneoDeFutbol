package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;

public class EquipoDTO {
    private long idEquipo;
    private String nombreEquipo;
    private String nombreTorneo;

    public EquipoDTO(long idEquipo, String nombreEquipo, String nombreTorneo) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.nombreTorneo = nombreTorneo;
    }

    public EquipoDTO() {

    }

    public long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }
}
