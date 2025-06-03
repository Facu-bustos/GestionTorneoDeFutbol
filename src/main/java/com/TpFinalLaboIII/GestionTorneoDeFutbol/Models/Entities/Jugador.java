package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.POSICION;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@ToString
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJugador;
    @NotBlank
    private String nombre;
    @NotNull
    private long numeroCamiseta;
    @NotNull
    @Enumerated(EnumType.STRING)
    private POSICION posicion;

    // Bidireccional
    @ManyToOne()
    @JoinColumn(name = "id_Equipo",
    referencedColumnName = "idEquipo")
    private Equipo equipo;


    public Jugador(long idJugador, String nombre, long numeroCamiseta, POSICION posicion, Equipo equipo) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.numeroCamiseta = numeroCamiseta;
        this.posicion = posicion;
        this.equipo = equipo;
    }

    public Jugador()
    {

    }


    public long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(long idJugador) {
        this.idJugador = idJugador;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    @NotNull
    public long getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(@NotNull long numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public @NotNull POSICION getPosicion() {
        return posicion;
    }

    public void setPosicion(@NotNull POSICION posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
