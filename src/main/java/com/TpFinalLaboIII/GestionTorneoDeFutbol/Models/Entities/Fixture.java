package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOPARTIDO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
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
    @NotNull
    private long golesEquipo1;
    @NotNull
    private long golesEquipo2;

    @NotNull
    private LocalDateTime fechaPartido;

    @NotNull
    @Enumerated(EnumType.STRING)
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
    @OneToMany(mappedBy = "fixture",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstadisticaGoleador> goleadores;
}
