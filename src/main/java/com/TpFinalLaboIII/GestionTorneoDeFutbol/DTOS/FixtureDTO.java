package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.EstadisticaGoleador;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Fixture;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOPARTIDO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

public class FixtureDTO {

    private long idFixture;
    private long golesEquipo1;
    private long golesEquipo2;
    private LocalDateTime fechaPartido;
    private ESTADOPARTIDO estadopartido;
    private EquipoDTO local;
    private EquipoDTO visitante;
    private Torneo nombreTorneo;

    public FixtureDTO(Fixture fixture) {
    }


    public long getIdFixture() {
        return idFixture;
    }

    public void setIdFixture(long idFixture) {
        this.idFixture = idFixture;
    }

    public long getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(long golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public long getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(long golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public LocalDateTime getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(LocalDateTime fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public ESTADOPARTIDO getEstadopartido() {
        return estadopartido;
    }

    public void setEstadopartido(ESTADOPARTIDO estadopartido) {
        this.estadopartido = estadopartido;
    }

    public EquipoDTO getLocal() {
        return local;
    }

    public void setLocal(EquipoDTO local) {
        this.local = local;
    }

    public EquipoDTO getVisitante() {
        return visitante;
    }

    public void setVisitante(EquipoDTO visitante) {
        this.visitante = visitante;
    }

    public Torneo getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(Torneo nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }
}
