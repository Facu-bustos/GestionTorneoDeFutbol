package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOTORNEO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString()
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTorneo;
    @NotBlank
    private String nombre;

    @NotNull
    private LocalDateTime FechaInicio;

    @NotNull
    private LocalDateTime FechaFin;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESTADOTORNEO estadotorneo;

    // Bidireccional
    @JsonManagedReference
    @OneToMany(mappedBy = "nombreTorneo" , cascade = CascadeType.ALL, orphanRemoval = true)//  <-- podemos usar optional false
    //para asegurar que si o si hay que cargar equipos
    private List<Equipo> equipos;
    //Bidireccional
    @OneToMany(mappedBy = "nombreTorneo", cascade = CascadeType.ALL, orphanRemoval = true) //  <-- podemos usar optional false
    //para asegurar que si o si hay que cargar un fixture
    private List<Fixture>fixture;

    public long getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(long idTorneo) {
        this.idTorneo = idTorneo;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public @NotNull LocalDateTime getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(@NotNull LocalDateTime fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public @NotNull LocalDateTime getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(@NotNull LocalDateTime fechaFin) {
        FechaFin = fechaFin;
    }

    public @NotNull ESTADOTORNEO getEstadotorneo() {
        return estadotorneo;
    }

    public void setEstadotorneo(@NotNull ESTADOTORNEO estadotorneo) {
        this.estadotorneo = estadotorneo;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public List<Fixture> getFixture() {
        return fixture;
    }

    public void setFixture(List<Fixture> fixture) {
        this.fixture = fixture;
    }
}
