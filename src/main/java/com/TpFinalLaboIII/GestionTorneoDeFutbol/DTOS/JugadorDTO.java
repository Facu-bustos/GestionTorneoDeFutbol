package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.POSICION;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class JugadorDTO {

    private long idJugador;
    private String nombre;
    @NotNull(message = "El id del equipo no puede ser null")
    @Positive(message = "El id del equipo debe ser un número positivo")
    private long numeroCamiseta;
    private POSICION posicion;
    private long idEquipo;


    public JugadorDTO(long idJugador, String nombre, long numeroCamiseta, POSICION posicion, long idEquipo) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.numeroCamiseta = numeroCamiseta;
        this.posicion = posicion;
        this.idEquipo = idEquipo;
    }

    public JugadorDTO() {

    }

    public long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(long idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(long numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public POSICION getPosicion() {
        return posicion;
    }

    public void setPosicion(POSICION posicion) {
        this.posicion = posicion;
    }

    public long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(long idEquipo) {
        this.idEquipo = idEquipo;
    }
}
