package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTILODEJUEGO;

public class DtDTO {

    private String nombre;
    private Equipo equipo;
    private ESTILODEJUEGO estilodejuego;

    public DtDTO(String nombre, Equipo equipo, ESTILODEJUEGO estilodejuego) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.estilodejuego = estilodejuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public ESTILODEJUEGO getEstilodejuego() {
        return estilodejuego;
    }

    public void setEstilodejuego(ESTILODEJUEGO estilodejuego) {
        this.estilodejuego = estilodejuego;
    }
}
