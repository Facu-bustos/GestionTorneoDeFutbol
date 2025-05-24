package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTILODEJUEGO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DT{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDT;
    @NotBlank
    private String nombre;

    //Relacion 1 a 1
    @OneToOne(mappedBy = "dt", cascade = CascadeType.PERSIST)
    private Equipo equipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESTILODEJUEGO  estilodejuego;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ROLEUSER roleuser ;
}
