package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.JugadorDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesJugador;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class JugadorController {

    @Autowired
    private final ServicesJugador servicesJugador;

    public JugadorController(ServicesJugador servicesJugador) {
        this.servicesJugador = servicesJugador;
    }

    @PostMapping("addPlayer")
    public ResponseEntity<String>AssignamentPlayerToTeam(@RequestBody JugadorDTO jugador) throws NotPostException, NotFoundException
    {
        return servicesJugador.addPlayerToTeam(jugador);
    }




}
