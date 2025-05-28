package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesJugador {
    @Autowired
    private final IRepositoryPlayer iRepositoryPlayer;

    public ServicesJugador(IRepositoryPlayer iRepositoryPlayer) {
        this.iRepositoryPlayer = iRepositoryPlayer;
    }



}
