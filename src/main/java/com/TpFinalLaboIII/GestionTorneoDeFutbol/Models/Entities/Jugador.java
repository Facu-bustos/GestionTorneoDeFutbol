package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.POSICION;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String nombre;
    @NotBlank
    private long numeroCamiseta;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private POSICION posicion;

    // Bidireccional
    @ManyToOne()
    @JoinColumn(name = "id_Equipo",
    referencedColumnName = "idEquipo")
    private Equipo equipo;

}
