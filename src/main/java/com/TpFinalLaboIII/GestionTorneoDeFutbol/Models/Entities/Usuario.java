package com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;

public abstract class Usuario {
    private String username;
    private String password;
    private String email;
    private ROLEUSER roleuser;
}
