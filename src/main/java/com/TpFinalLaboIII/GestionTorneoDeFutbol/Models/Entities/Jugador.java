package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.POSICION;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJugador;
    @NonNull
    private String nombre;
    @NonNull
    private long numeroCamiseta;
    @NonNull
    private POSICION posicion;

    // Bidireccional
    @ManyToOne()
    @JoinColumn(name = "id_Equipo",
    referencedColumnName = "idEquipo")
    private Equipo equipo;


}
