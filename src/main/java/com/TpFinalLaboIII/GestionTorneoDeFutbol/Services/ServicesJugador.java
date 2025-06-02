package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.JugadorDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Jugador;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.POSICION;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ServicesJugador {
    @Autowired
    private final IRepositoryPlayer iRepositoryPlayer;
    @Autowired
    private final ServicesEquipo servicesEquipo;

    public ServicesJugador(IRepositoryPlayer iRepositoryPlayer, ServicesEquipo servicesEquipo) {
        this.iRepositoryPlayer = iRepositoryPlayer;
        this.servicesEquipo = servicesEquipo;
    }


    public ResponseEntity<String>addPlayerToTeam(@RequestBody JugadorDTO jugadorDTO) throws NotPostException, NotFoundException {
        if (jugadorDTO.getNombre() == null) {
            throw new NotPostException("Error, datos de jugador incompletos");
        }

        if(jugadorDTO.getPosicion() != POSICION.DEFENSOR && jugadorDTO.getPosicion() != POSICION.MEDIOCAMPISTA && jugadorDTO.getPosicion() != POSICION.DELANTERO)
        {
            throw new NotPostException("Error, posicion del jugador erronea");
        }

       if(jugadorDTO.getNumeroCamiseta()<0)
       {
           long numeroAleatorio = (int)(Math.random() * 100);
           jugadorDTO.setNumeroCamiseta(numeroAleatorio);

       }

        if(jugadorDTO.getIdEquipo() <0)
        {
            throw new NotPostException("Error, el numero es negativo");
        }

        Equipo equipo = servicesEquipo.teamByID(jugadorDTO.getIdEquipo()).orElseThrow(() -> new NotFoundException("ID de equipo inexistente"));
        if(equipo.getJugadores().size()>=11)
        {
            throw new NotPostException("La cantidad de jugadores maxima por equipo son 11");
        }

        Jugador jugador = new Jugador();
        jugador.setNombre(jugadorDTO.getNombre());
        jugador.setNumeroCamiseta(jugadorDTO.getNumeroCamiseta());
        jugador.setPosicion(jugadorDTO.getPosicion());
        jugador.setEquipo(equipo);

        iRepositoryPlayer.save(jugador);

        return ResponseEntity.ok("Jugador Asignado al equipo al equipo: " + equipo.getNombre());
    }

    public void deletePlayerList(List<Jugador>listaJugadores)
    {
        for(Jugador j : listaJugadores)
        {
            iRepositoryPlayer.delete(j);
        }
    }

}
