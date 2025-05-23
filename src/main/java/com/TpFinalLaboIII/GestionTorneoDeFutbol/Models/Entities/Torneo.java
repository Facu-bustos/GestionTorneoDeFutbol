package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOTORNEO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Torneo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idTorneo;
    @NotBlank
    private String nombre;
    @NotBlank
    private Date FechaInicio;
    @NotBlank
    private Date FechaFin;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private ESTADOTORNEO estadotorneo;

    // Bidireccional
    @OneToMany(mappedBy = "nombreTorneo" , cascade = CascadeType.PERSIST)//  <-- podemos usar optional false
    //para asegurar que si o si hay que cargar equipos
    private List<Equipo> equipos;
    //Bidireccional
    @OneToMany(mappedBy = "nombreTorneo", cascade = CascadeType.PERSIST) //  <-- podemos usar optional false
    //para asegurar que si o si hay que cargar un fixture
    private List<Fixture>fixture;
}
