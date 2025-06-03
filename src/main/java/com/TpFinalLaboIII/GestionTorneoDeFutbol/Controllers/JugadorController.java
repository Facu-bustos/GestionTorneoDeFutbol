package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.JugadorDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesJugador;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class JugadorController {

    @Autowired
    private final ServicesJugador servicesJugador;

    public JugadorController(ServicesJugador servicesJugador) {
        this.servicesJugador = servicesJugador;
    }

    @PostMapping("/addPlayer")
    public ResponseEntity<String>AssignamentPlayerToTeam(@RequestBody JugadorDTO jugador) throws NotPostException, NotFoundException
    {
        return servicesJugador.addPlayerToTeam(jugador);
    }

    @PostMapping("/add/List/Player")
    public ResponseEntity<String>AssignamentListPlayerToTeam(@RequestBody List<JugadorDTO> jugadorDTOList) throws NotFoundException, NotPostException
    {
        return servicesJugador.addListPlayerToTeam(jugadorDTOList);
    }

    @GetMapping("/get/player/byID/{idJugador}")
    @ResponseBody
    public JugadorDTO getPlayerDTO(@PathVariable long idJugador) throws NotFoundException
    {
        return servicesJugador.getPlayerDTO(idJugador);
    }

    @PutMapping("/update/player/{idPlayer}")
    public ResponseEntity<String>updatePlayer(@PathVariable long idPlayer, @RequestBody JugadorDTO jugadorDTO) throws NotPostException, NotFoundException
    {
        return servicesJugador.updatePlayer(idPlayer,jugadorDTO);
    }

    @DeleteMapping("delete/player/byID/{idPlayer}")
    public ResponseEntity<String>deletePlayer(@PathVariable long idPlayer) throws NotFoundException
    {
        return servicesJugador.deletePlayer(idPlayer);
    }

    @GetMapping("/allPlayers")
    @ResponseBody
    public List<JugadorDTO>allPlayers() throws NotFoundException
    {
        return servicesJugador.getAllPlayer();
    }

}
