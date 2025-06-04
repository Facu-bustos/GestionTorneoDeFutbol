package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOFIXTURE;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOPARTIDO;
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
@ToString
public class Fixture {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idFixture;
    @NotNull
    private long golesEquipo1;
    @NotNull
    private long golesEquipo2;

    @NotNull
    private LocalDateTime fechaPartido;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESTADOPARTIDO estadopartido;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESTADOFIXTURE estadofixture;

    public @NotNull ESTADOFIXTURE getEstadofixture() {
        return estadofixture;
    }

    public void setEstadofixture(@NotNull ESTADOFIXTURE estadofixture) {
        this.estadofixture = estadofixture;
    }

    //birideccional
    @ManyToOne()
    @JoinColumn(name = "id_Equipo_local", referencedColumnName = "idEquipo")
    private Equipo local;
    //birideccional
    @ManyToOne()
    @JoinColumn(name = "id_Equipo_visitante", referencedColumnName = "idEquipo")
    private Equipo visitante;

    //birideccional
    @ManyToOne()
    @JoinColumn(name = "id_Torneo",
    referencedColumnName = "idTorneo")
    private Torneo nombreTorneo;

    //birideccional
    @OneToMany(mappedBy = "fixture",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstadisticaGoleador> goleadores;


    public long getIdFixture() {
        return idFixture;
    }

    public void setIdFixture(long idFixture) {
        this.idFixture = idFixture;
    }

    @NotNull
    public long getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(@NotNull long golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    @NotNull
    public long getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(@NotNull long golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public @NotNull LocalDateTime getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(@NotNull LocalDateTime fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public @NotNull ESTADOPARTIDO getEstadopartido() {
        return estadopartido;
    }

    public void setEstadopartido(@NotNull ESTADOPARTIDO estadopartido) {
        this.estadopartido = estadopartido;
    }

    public Equipo getLocal() {
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    public Torneo getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(Torneo nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public List<EstadisticaGoleador> getGoleadores() {
        return goleadores;
    }

    public void setGoleadores(List<EstadisticaGoleador> goleadores) {
        this.goleadores = goleadores;
    }
}
