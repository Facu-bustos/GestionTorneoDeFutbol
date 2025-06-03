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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        if(jugadorDTO.getPosicion() != POSICION.DEFENSOR && jugadorDTO.getPosicion() != POSICION.MEDIOCAMPISTA && jugadorDTO.getPosicion() != POSICION.DELANTERO && jugadorDTO.getPosicion()!= POSICION.ARQUERO)
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

    public JugadorDTO getPlayerDTO(@PathVariable long idJugador) throws NotFoundException
    {
        Jugador jugador = iRepositoryPlayer.findById(idJugador).orElseThrow(() -> new NotFoundException("ID jugador inexistente"));

        JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setIdJugador(jugador.getIdJugador());
        jugadorDTO.setNombre(jugador.getNombre());
        jugadorDTO.setNumeroCamiseta(jugador.getNumeroCamiseta());
        jugadorDTO.setPosicion(jugador.getPosicion());
        jugadorDTO.setIdEquipo(jugador.getEquipo().getIdEquipo());
        return jugadorDTO;
    }

    public ResponseEntity<String>addListPlayerToTeam(@RequestBody List<JugadorDTO>jugadorDTOList) throws NotPostException,NotFoundException
    {


        for(JugadorDTO jDTO : jugadorDTOList)
        {
            if(jDTO.getNombre()== null)
            {
                throw new NotPostException("Error, NOMBRE ERRONEO" + " ID:" + jDTO.getIdJugador() );
            }
            if(jDTO.getPosicion() != POSICION.DEFENSOR && jDTO.getPosicion() != POSICION.MEDIOCAMPISTA && jDTO.getPosicion() != POSICION.DELANTERO && jDTO.getPosicion() != POSICION.ARQUERO)
            {
                throw new NotPostException("Error, POSICION DEL JUGADOR ERRONEA " + " ID: " + jDTO.getIdJugador());
            }
            if(jDTO.getIdEquipo()<0)
            {
                throw new NotPostException("Error,el ID del equipo no puede ser negativo");
            }
            if(jDTO.getNumeroCamiseta()<=0)
            {
                long numeroAleatorio = (int)(Math.random() * 100);
                jDTO.setNumeroCamiseta(numeroAleatorio);
            }

            Equipo equipo = servicesEquipo.teamByID(jDTO.getIdEquipo()).orElseThrow(()-> new NotFoundException("Error el id del equipo no existe"));
            long cantidadJugadores = equipo.getJugadores().size();
            long nuevos = jugadorDTOList.stream()
                    .filter(j-> j.getIdEquipo() == equipo.getIdEquipo())
                    .count();

            if(cantidadJugadores + nuevos > 11)
            {
                throw new NotPostException("El equipo superaria los 11 jugadores");
            }

            Jugador j = new Jugador();
            j.setNombre(jDTO.getNombre());
            j.setIdJugador(jDTO.getIdJugador());
            j.setPosicion(jDTO.getPosicion());
            j.setNumeroCamiseta(jDTO.getNumeroCamiseta());
            j.setEquipo(equipo);
            iRepositoryPlayer.save(j);
        }

        return ResponseEntity.ok("Lista de jugadores cargada correctamente");
    }

    public ResponseEntity<String>updatePlayer(@PathVariable long idPlayer, @RequestBody JugadorDTO jugadorDTO) throws NotPostException, NotFoundException
    {
        Jugador jugador = iRepositoryPlayer.findById(idPlayer).orElseThrow(() -> new NotFoundException("Error, ID jugador no existe en la base de datos"));
        Optional<Equipo>equipo = servicesEquipo.teamByID(jugadorDTO.getIdEquipo());
        if(equipo.isEmpty())
        {
            throw new NotFoundException("Error, ID equipo no existe en la base de datos");
        }

        jugador.setNombre(jugadorDTO.getNombre());
        jugador.setPosicion(jugadorDTO.getPosicion());
        jugador.setNumeroCamiseta(jugadorDTO.getNumeroCamiseta());
        jugador.setEquipo(equipo.get());
        iRepositoryPlayer.save(jugador);
        return ResponseEntity.ok("Jugador ID: " + idPlayer + " Actualizado");
    }

    public ResponseEntity<String>deletePlayer(@PathVariable long idPlayer)throws NotFoundException
    {
        Jugador jugador = iRepositoryPlayer.findById(idPlayer).orElseThrow(() -> new NotFoundException("Error, id de jugador no existe en la base de datos"));
        iRepositoryPlayer.delete(jugador);
        return ResponseEntity.ok("Jugador ID: " + jugador.getIdJugador() + "Eliminado");
    }

    public List<JugadorDTO>getAllPlayer() throws NotFoundException
    {
        List<Jugador>allJugadores = iRepositoryPlayer.findAll();
        if(allJugadores.isEmpty())
        {
            throw new NotFoundException("No se encuentran jugadores en la base de datos");
        }

        List<JugadorDTO>allJugadorDTO= new ArrayList<>();

        for(Jugador j: allJugadores)
        {
            JugadorDTO jugadorDTO = new JugadorDTO();
            jugadorDTO.setIdEquipo(j.getEquipo().getIdEquipo());
            jugadorDTO.setIdJugador(j.getIdJugador());
            jugadorDTO.setNombre(j.getNombre());
            jugadorDTO.setPosicion(j.getPosicion());
            jugadorDTO.setNumeroCamiseta(j.getNumeroCamiseta());
            allJugadorDTO.add(jugadorDTO);
        }
        return allJugadorDTO;
    }

}
