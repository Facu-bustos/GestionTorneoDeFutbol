package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Equipo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idEquipo;
    @NonNull
    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "id_Torneo", referencedColumnName = "idTorneo")
    private Torneo nombreTorneo;

    @OneToMany(mappedBy = "equipo",cascade = CascadeType.PERSIST)
    private List<Jugador> jugadores;

    @OneToMany(mappedBy = "local",cascade = CascadeType.PERSIST)
    private List<Fixture>fixtureComoLocal;

    @OneToMany(mappedBy = "visitante",cascade = CascadeType.PERSIST)
    private List<Fixture>fixtureComoVisitante;

    //Relacion 1 a 1
    @OneToOne()
    @JoinColumn(name = "id_DT", referencedColumnName = "idDT")
    private DT dt;

    //agregar capitan... no saber si boolean o asignado
}
