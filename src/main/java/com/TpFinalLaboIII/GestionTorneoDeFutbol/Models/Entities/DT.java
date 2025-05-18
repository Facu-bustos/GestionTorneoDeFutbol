package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTILODEJUEGO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DT extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDT;
    //Relacion 1 a 1
    @OneToOne(mappedBy = "dt", cascade = CascadeType.PERSIST)
    private Equipo equipo;
    @NonNull
    private ESTILODEJUEGO  estilodejuego;
}
