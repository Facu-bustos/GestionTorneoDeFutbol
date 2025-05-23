package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import jakarta.persistence.*;
import lombok.*;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@DiscriminatorValue("ADMIN")
public class Administrador extends Usuario{

}
