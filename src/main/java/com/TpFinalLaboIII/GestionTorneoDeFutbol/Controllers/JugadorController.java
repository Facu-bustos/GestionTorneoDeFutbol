package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesJugador;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/player")
public class JugadorController {

    @Autowired
    private final ServicesJugador servicesJugador;

    public JugadorController(ServicesJugador servicesJugador) {
        this.servicesJugador = servicesJugador;
    }




}
