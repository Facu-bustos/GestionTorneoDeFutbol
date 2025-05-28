package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Fixture;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOTORNEO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class TorneoDTO {

    private long idTorneo;
    private String nombre;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private ESTADOTORNEO estadotorneo;

    public TorneoDTO(long idTorneo, String nombre, LocalDateTime fechaInicio, LocalDateTime fechaFin, ESTADOTORNEO estadotorneo) {
        this.idTorneo = idTorneo;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadotorneo = estadotorneo;
    }

    public TorneoDTO()
    {

    }

    public long getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(long idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ESTADOTORNEO getEstadotorneo() {
        return estadotorneo;
    }

    public void setEstadotorneo(ESTADOTORNEO estadotorneo) {
        this.estadotorneo = estadotorneo;
    }
}
