package com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTILODEJUEGO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;

public class ViewUserDTDTO {
    private long idDt;
    private String nombre;
    private Equipo equipo;
    private ESTILODEJUEGO estilodejuego;
    private String email;
    private ROLEUSER roleuser;

    public ViewUserDTDTO(String email, Equipo equipo, ESTILODEJUEGO estilodejuego, long idDt, String nombre, ROLEUSER roleuser) {
        this.email = email;
        this.equipo = equipo;
        this.estilodejuego = estilodejuego;
        this.idDt = idDt;
        this.nombre = nombre;
        this.roleuser = roleuser;
    }

    public ViewUserDTDTO() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ROLEUSER getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(ROLEUSER roleuser) {
        this.roleuser = roleuser;
    }
}
