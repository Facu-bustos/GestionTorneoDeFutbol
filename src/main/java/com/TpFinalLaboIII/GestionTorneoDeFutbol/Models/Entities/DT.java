package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTILODEJUEGO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DT{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDT;
    @NotBlank
    private String nombre;

    //Relacion 1 a 1
    @OneToOne(mappedBy = "dt", cascade = CascadeType.ALL , orphanRemoval = true)
    private Equipo equipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESTILODEJUEGO  estilodejuego;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ROLEUSER roleuser ;

    public long getIdDT() {
        return idDT;
    }

    public void setIdDT(long idDT) {
        this.idDT = idDT;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public @NotNull ESTILODEJUEGO getEstilodejuego() {
        return estilodejuego;
    }

    public void setEstilodejuego(@NotNull ESTILODEJUEGO estilodejuego) {
        this.estilodejuego = estilodejuego;
    }

    public @NotNull ROLEUSER getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(@NotNull ROLEUSER roleuser) {
        this.roleuser = roleuser;
    }
}
