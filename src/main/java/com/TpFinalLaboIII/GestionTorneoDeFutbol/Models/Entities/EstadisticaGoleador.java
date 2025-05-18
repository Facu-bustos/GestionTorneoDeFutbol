package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EstadisticaGoleador {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idEstadistica;
    private long idJugador;
    private long cantidadGoles;
    private long idEquipo;

    //Birideccional
    @ManyToOne()
    @JoinColumn(name = "id_Fixture",
    referencedColumnName = "idFixture")
    private Fixture fixture;
}
