package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EstadisticaGoleador {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idEstadistica;
    private long idJugador;
    private long cantidadGoles;
    private long idEquipo;

    //Birideccional
    @ManyToOne()
    @JoinColumn(name = "id_Fixture",
    referencedColumnName = "idFixture")
    private Fixture fixture;


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

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }
}
