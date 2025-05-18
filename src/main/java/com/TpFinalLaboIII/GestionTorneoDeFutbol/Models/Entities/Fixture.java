package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOPARTIDO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Fixture {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idFixture;
    @NonNull
    private long golesEquipo1;
    @NonNull
    private long golesEquipo2;
    @NonNull
    private Date fechaPartido;
    @NonNull
    private ESTADOPARTIDO estadopartido;

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
    @OneToMany(mappedBy = "fixture",cascade = CascadeType.PERSIST)
    private List<EstadisticaGoleador> goleadores;
}
