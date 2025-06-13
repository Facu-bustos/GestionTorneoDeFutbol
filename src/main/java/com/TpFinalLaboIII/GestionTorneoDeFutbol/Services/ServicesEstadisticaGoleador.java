package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EstadisticasGoleadorDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.JugadorDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.*;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOFIXTURE;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOPARTIDO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryFixture;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryPlayer;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryTeam;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryeEstadisticaGoleador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.JarURLConnection;
import java.util.*;

@Service
public class ServicesEstadisticaGoleador {

    @Autowired
    private final IRepositoryeEstadisticaGoleador iRepositoryeEstadisticaGoleador;
    @Autowired
    private final IRepositoryTeam iRepositoryTeam;
    @Autowired
    private final IRepositoryFixture iRepositoryFixture;
    @Autowired
    final  IRepositoryPlayer iRepositoryPlayer;
    @Autowired
    private final ServicesTorneo servicesTorneo;


    public ServicesEstadisticaGoleador(IRepositoryeEstadisticaGoleador iRepositoryeEstadisticaGoleador, IRepositoryTeam iRepositoryTeam, IRepositoryFixture iRepositoryFixture, IRepositoryPlayer iRepositoryPlayer, ServicesTorneo servicesTorneo) {
        this.iRepositoryeEstadisticaGoleador = iRepositoryeEstadisticaGoleador;
        this.iRepositoryTeam = iRepositoryTeam;
        this.iRepositoryFixture = iRepositoryFixture;
        this.iRepositoryPlayer = iRepositoryPlayer;
        this.servicesTorneo = servicesTorneo;
    }

    public ResponseEntity<String>generateStatistics(@PathVariable long idTorneo)throws NotFoundException, NotPostException
    {
        Torneo torneo =  servicesTorneo.torneoExistAndPresent(idTorneo);
        if(torneo == null)
        {
            throw new NotFoundException("Error, el ID torneo no existe.");
        }

        List<Fixture>fix = torneo.getFixture();
       if(fix.isEmpty())
       {
           throw new NotFoundException("El torneo no tiene un fixture realizado");
       }

       for(Fixture f: fix)
       {
           asignadorDeGoles(f.getLocal().getIdEquipo(),f.getGolesEquipo1(),f);
           asignadorDeGoles(f.getVisitante().getIdEquipo(),f.getGolesEquipo2(),f);
       }

       return ResponseEntity.ok("Estadistica goleadores generados correctamente");
    }


    public void asignadorDeGoles(Long idEquipo, long goles,Fixture fixture) throws NotFoundException, NotPostException
    {
        if(goles <=0 )
        {
            return;
        }

        if(fixture.getEstadopartido()== ESTADOPARTIDO.PENDIENTE)
        {
            throw new NotPostException("Error, el partido aun no se juega");
        }

        Equipo equipo = iRepositoryTeam.findById(idEquipo).orElseThrow(()-> new NotFoundException("El equipo no se encuenta en la base de datos"));

        List<Jugador>jugadores = equipo.getJugadores();
        if(jugadores.isEmpty())
        {
            throw new NotFoundException("La lista de jugadores se encuenta vacia");
        }

        Random random = new Random();
        Map<Long, Long> golesPorJugador = new HashMap<>();

        // Repartir goles aleatoriamente
        for (int i = 0; i < goles; i++) {
            Jugador jugador = jugadores.get(random.nextInt(jugadores.size()));
            golesPorJugador.put(jugador.getIdJugador(), golesPorJugador.getOrDefault(jugador.getIdJugador(), 0L) + 1);
        }

        for (Map.Entry<Long, Long> entry : golesPorJugador.entrySet()) {
            EstadisticaGoleador estadistica = new EstadisticaGoleador();
            estadistica.setIdJugador(entry.getKey());
            estadistica.setIdEquipo(equipo.getIdEquipo());
            estadistica.setCantidadGoles(entry.getValue());
            estadistica.setFixture(fixture);
            iRepositoryeEstadisticaGoleador.save(estadistica);
            fixture.setEstadofixture(ESTADOFIXTURE.TERMINADO);
        }
        iRepositoryFixture.save(fixture);
    }

    public List<EstadisticasGoleadorDTO>getStatistcs(@PathVariable long idTorneo) throws NotFoundException
    {
        List<EstadisticaGoleador>estadisticas = iRepositoryeEstadisticaGoleador.findAll();

        if(estadisticas.isEmpty())
        {
            throw new NotFoundException("Error, no encontraron estadisticas en el sistema");
        }

        estadisticas.sort(Comparator.comparing(EstadisticaGoleador::getCantidadGoles).reversed());

        List<EstadisticasGoleadorDTO>estadisticasGoleadorDTOS = new ArrayList<>();
        for(EstadisticaGoleador e: estadisticas)
        {
            EstadisticasGoleadorDTO estaDTO = new EstadisticasGoleadorDTO();

            Optional<Jugador> jugador = iRepositoryPlayer.findById(e.getIdJugador());
            Optional<Equipo>equipo = iRepositoryTeam.findByIdEquipo(e.getIdEquipo());
            if(jugador.isPresent() && equipo.isPresent())
            {
                estaDTO.setIdEstadistica(e.getIdEstadistica());
                estaDTO.setCantidadGoles(e.getCantidadGoles());
                estaDTO.setIdJugador(e.getIdJugador());
                estaDTO.setNombreJugador(jugador.get().getNombre());
                estaDTO.setEquipo(equipo.get().getNombre());
                estaDTO.setIdEquipo(e.getIdEquipo());
                estadisticasGoleadorDTOS.add(estaDTO);
            }
        }
        return estadisticasGoleadorDTOS;
    }

}
