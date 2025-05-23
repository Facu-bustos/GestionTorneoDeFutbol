package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTILODEJUEGO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import jakarta.persistence.*;
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
    @NonNull
    private String nombre;

    //Relacion 1 a 1
    @OneToOne(mappedBy = "dt", cascade = CascadeType.PERSIST)
    private Equipo equipo;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ESTILODEJUEGO  estilodejuego;

    @Enumerated(EnumType.STRING)
    private ROLEUSER roleuser ;
}
