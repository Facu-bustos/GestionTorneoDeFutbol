package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOPARTIDO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
public class FixtureDTOView {
        private long idFixture;
        private LocalDateTime fechaPartido;
        private ESTADOPARTIDO estadopartido;
        private String local;
        private String visitante;
        private String nombreTorneo;


    public long getIdFixture() {
        return idFixture;
    }

    public void setIdFixture(long idFixture) {
        this.idFixture = idFixture;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }
}
