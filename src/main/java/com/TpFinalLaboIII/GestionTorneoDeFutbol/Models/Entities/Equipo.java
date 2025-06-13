package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Entity
@Builder
@ToString
public class Equipo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idEquipo;
    @NotBlank
    private String nombre;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "id_Torneo", referencedColumnName = "idTorneo")
    private Torneo nombreTorneo;

    @OneToMany(mappedBy = "equipo",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jugador> jugadores;

    @OneToMany(mappedBy = "local",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fixture>fixtureComoLocal;

    @OneToMany(mappedBy = "visitante",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fixture>fixtureComoVisitante;

    //Relacion 1 a 1
    @OneToOne
    @JoinColumn(name = "id_DT", referencedColumnName = "idDT")
    private DT dt;

    public Equipo(long idEquipo, String nombre, Torneo nombreTorneo, List<Jugador> jugadores, List<Fixture> fixtureComoLocal, List<Fixture> fixtureComoVisitante, DT dt) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.nombreTorneo = nombreTorneo;
        this.jugadores = jugadores;
        this.fixtureComoLocal = fixtureComoLocal;
        this.fixtureComoVisitante = fixtureComoVisitante;
        this.dt = dt;
    }
    public Equipo()
    {

    }

    public long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public Torneo getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(Torneo nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Fixture> getFixtureComoLocal() {
        return fixtureComoLocal;
    }

    public void setFixtureComoLocal(List<Fixture> fixtureComoLocal) {
        this.fixtureComoLocal = fixtureComoLocal;
    }

    public List<Fixture> getFixtureComoVisitante() {
        return fixtureComoVisitante;
    }

    public void setFixtureComoVisitante(List<Fixture> fixtureComoVisitante) {
        this.fixtureComoVisitante = fixtureComoVisitante;
    }

    public DT getDt() {
        return dt;
    }

    public void setDt(DT dt) {
        this.dt = dt;
    }
}
