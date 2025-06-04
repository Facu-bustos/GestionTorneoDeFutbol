package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOPARTIDO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class FixtureDTOUpdate {
    private long idFixture;
    private ESTADOPARTIDO estadopartido;
    private long golesLocal;
    private long golesVisitante;

    public long getIdFixture() {
        return idFixture;
    }

    public void setIdFixture(long idFixture) {
        this.idFixture = idFixture;
    }

    public ESTADOPARTIDO getEstadopartido() {
        return estadopartido;
    }

    public void setEstadopartido(ESTADOPARTIDO estadopartido) {
        this.estadopartido = estadopartido;
    }

    public long getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(long golesLocal) {
        this.golesLocal = golesLocal;
    }

    public long getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(long golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
}
