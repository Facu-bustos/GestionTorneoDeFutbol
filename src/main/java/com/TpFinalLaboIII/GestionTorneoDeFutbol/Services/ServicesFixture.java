package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.*;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Fixture;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOFIXTURE;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOPARTIDO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOTORNEO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTILODEJUEGO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryFixture;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryTournaumet;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ServicesFixture {
    @Autowired
    private final IRepositoryFixture iRepositoryFixture;
    @Autowired
    private final IRepositoryTournaumet iRepositoryTournaumet;
    @Autowired
    private final ServicesTorneo servicesTorneo;


    public ServicesFixture(IRepositoryFixture iRepositoryFixture, IRepositoryTournaumet iRepositoryTournaumet, ServicesTorneo servicesTorneo) {
        this.iRepositoryFixture = iRepositoryFixture;
        this.iRepositoryTournaumet = iRepositoryTournaumet;
        this.servicesTorneo = servicesTorneo;
    }

    public ResponseEntity<String>gerenateFixture(@PathVariable long idTorneo) throws NotFoundException, NotPostException
    {
        Torneo torneo = servicesTorneo.torneoExistAndPresent(idTorneo);
        if(torneo == null)
        {
            throw new NotFoundException("No existe torneo con ese ID para desarrollar el fixture");
        }
        if(torneo.getEstadotorneo() == ESTADOTORNEO.COMENZADO || torneo.getEstadotorneo() == ESTADOTORNEO.TERMINADO)
        {
            throw  new NotPostException("Ya se genero el fixture de este torneo");
        }

        List<Equipo>equipos = torneo.getEquipos();
        if(equipos.size() != 4)
        {
            throw new NotPostException("Error, no hay equipos suficientes para generar el fixture ");
        }
        List<Fixture> fixtures = new ArrayList<>();
        LocalDateTime fechaBase=LocalDateTime.now();

        int diasEntrePartidos = 5;
        int contadorDeDias = 0;

        for(int i = 0; i<equipos.size(); i++)
        {
            for(int j=i+1; j<equipos.size(); j++)
            {
                Equipo equipo1 = equipos.get(i);
                Equipo equipo2 = equipos.get(j);


                Fixture fixtureIda = new Fixture();
                fixtureIda.setLocal(equipo1);
                fixtureIda.setVisitante(equipo2);
                fixtureIda.setFechaPartido(fechaBase.plusDays(contadorDeDias++ * diasEntrePartidos));
                fixtureIda.setEstadopartido(ESTADOPARTIDO.PENDIENTE);
                fixtureIda.setEstadofixture(ESTADOFIXTURE.PENDIENTE);
                fixtureIda.setGolesEquipo1(0);
                fixtureIda.setGolesEquipo2(0);
                fixtureIda.setNombreTorneo(torneo);
                fixtures.add(fixtureIda);


                Fixture fixtureVuelta = new Fixture();
                fixtureVuelta.setLocal(equipo2);
                fixtureVuelta.setVisitante(equipo1);
                fixtureVuelta.setFechaPartido(fechaBase.plusDays(contadorDeDias++ * diasEntrePartidos));
                fixtureVuelta.setEstadopartido(ESTADOPARTIDO.PENDIENTE);
                fixtureVuelta.setEstadofixture(ESTADOFIXTURE.PENDIENTE);
                fixtureVuelta.setGolesEquipo1(0);
                fixtureVuelta.setGolesEquipo2(0);
                fixtureVuelta.setNombreTorneo(torneo);
                fixtures.add(fixtureVuelta);
            }
        }

        iRepositoryFixture.saveAll(fixtures);
        torneo.setEstadotorneo(ESTADOTORNEO.COMENZADO);
        iRepositoryTournaumet.save(torneo);
        return ResponseEntity.ok("Fixture IDA y Vuelta generados");
    }


    public List<FixtureDTOView>getAllFixture(@PathVariable long idTorneo)throws NotFoundException {
        List<Fixture> fixtures = iRepositoryFixture.findByNombreTorneoIdTorneoOrderByFechaPartidoAsc(idTorneo);
        if (fixtures.isEmpty()) {
            throw new NotFoundException("Error, no se encuentran registros del fixture ID: " + idTorneo);
        }

        List<FixtureDTOView>fxView=new ArrayList<>();
        for(Fixture f: fixtures)
        {
            FixtureDTOView fx = new FixtureDTOView();
            fx.setIdFixture(f.getIdFixture());
            fx.setNombreTorneo(f.getNombreTorneo().getNombre());
            fx.setEstadopartido(f.getEstadopartido());
            fx.setLocal(f.getLocal().getNombre());
            fx.setVisitante(f.getVisitante().getNombre());
            fx.setFechaPartido(f.getFechaPartido());
            fxView.add(fx);
        }
        return fxView;
    }

    public ResponseEntity<String>generateFixtureResult(@PathVariable long idTorneo)throws NotFoundException, NotPostException
    {
        Torneo torneo = servicesTorneo.torneoExistAndPresent(idTorneo);
        if(torneo == null)
        {
            throw new NotFoundException("No existe ese id como torneo en la base de datos");
        }

        if(torneo.getEstadotorneo() == ESTADOTORNEO.TERMINADO || torneo.getEstadotorneo() == ESTADOTORNEO.PENDIENTE || torneo.getEstadotorneo() == ESTADOTORNEO.ESPERA)
        {
            throw new NotPostException("Error, el estado del torneo es incorrecto para generar los resultados del fixture");
        }

        List<Fixture>fixtures = torneo.getFixture();
        if(fixtures.isEmpty())
        {
            throw new NotFoundException("El torneo no tiene Fixtures Cargados..");
        }


        for(Fixture f: fixtures)
        {
            Equipo local = f.getLocal();
            Equipo visitante = f.getVisitante();

            int golesLocal = generadorDeGolesPorEstiloDeJuego(local.getDt().getEstilodejuego());
            int golesVisitante = generadorDeGolesPorEstiloDeJuego(visitante.getDt().getEstilodejuego());

            f.setGolesEquipo1(golesLocal);
            f.setGolesEquipo2(golesVisitante);
            f.setEstadopartido(ESTADOPARTIDO.TERMINADO);
        }



        iRepositoryFixture.saveAll(fixtures);
        torneo.setEstadotorneo(ESTADOTORNEO.TERMINADO);
        iRepositoryTournaumet.save(torneo);


        return ResponseEntity.ok("Los datos de los Fixtures correspondientes al torneo estan cargados correctamente");
    }


    public List<FixtureDTOViewEnd>getFixtureDtoEnd(@PathVariable long idTorneo) throws NotFoundException
    {
        Torneo torneo = servicesTorneo.torneoExistAndPresent(idTorneo);
        if(torneo == null)
        {
            throw new NotFoundException("¡Error! no existe ese id como torneo en la base de datos");
        }

        List<Fixture>fixtures = torneo.getFixture();
        if(fixtures.isEmpty())
        {
            throw new NotFoundException("Los datos de los Fixtures correspondientes al torneo estan cargados correctamente");
        }

        List<FixtureDTOViewEnd>fixtureDTOViewEnds = new ArrayList<>();
        for(Fixture f: fixtures)
        {
            if(f.getEstadopartido() == ESTADOPARTIDO.TERMINADO)
            {
                FixtureDTOViewEnd fx = new FixtureDTOViewEnd();
                fx.setLocal(f.getLocal().getNombre());
                fx.setGolesLocal(f.getGolesEquipo1());

                fx.setVisitante(f.getVisitante().getNombre());
                fx.setGolesVisitante(f.getGolesEquipo2());

                fx.setEstadopartido(f.getEstadopartido());
                fx.setIdFixture(f.getIdFixture());
                fx.setFechaPartido(f.getFechaPartido());
                fx.setNombreTorneo(f.getNombreTorneo().getNombre());
                fixtureDTOViewEnds.add(fx);
            }
        }
        return fixtureDTOViewEnds;
    }

    public ResponseEntity<String>updateFixtureExists(@RequestBody FixtureDTOUpdate fx) throws NotFoundException, NotPostException {

        if(fx.getIdFixture()<0)
        {
            throw new NotPostException("ERROR, idFixture negativo");
        }

        if(fx.getGolesLocal()<0 || fx.getGolesVisitante() < 0)
        {
            throw new NotPostException("Error, los goles visitantes o locales no pueden ser negativos");
        }

        Optional<Fixture> fix = iRepositoryFixture.findById(fx.getIdFixture());
        if (fix.isEmpty()) {
            throw new NotFoundException("ERROR, no se encuentran datos cargados en el fixture");
        }

        fix.get().setGolesEquipo1(fx.getGolesLocal());
        fix.get().setGolesEquipo2(fx.getGolesVisitante());
        fix.get().setEstadopartido(fx.getEstadopartido());
        iRepositoryFixture.save(fix.get());

        return ResponseEntity.ok("Fixture actualizado " + fx.getIdFixture());
    }


    public ResponseEntity<String>deleteFixtureId(@PathVariable long idFixture) throws NotFoundException
    {
        Optional<Fixture> fixture = iRepositoryFixture.findById(idFixture);
        if(fixture.isEmpty())
        {
            throw new NotFoundException("Error, no se encuentra ese fixture en la base de datos");
        }

        iRepositoryFixture.deleteById(idFixture);
        return ResponseEntity.ok("Fixture eliminado " + idFixture);
    }


    public ResponseEntity<String>deleteFixtureAllTournament(@PathVariable long idTorneo) throws NotFoundException
    {
        Torneo torneo = servicesTorneo.torneoExistAndPresent(idTorneo);
        if(torneo == null)
        {
            throw new NotFoundException("El ID torneo no existe en la base de datos");
        }

        torneo.getFixture().clear();
        iRepositoryTournaumet.save(torneo);

        return ResponseEntity.ok("El fixture completo fue eliminado con exito");
    }


    private int generadorDeGolesPorEstiloDeJuego(ESTILODEJUEGO estilodejuego)
    {
        Random random = new Random();
        switch (estilodejuego){
            case OFENSIVO -> {
                return random.nextInt(4)+2;
            }
            case EQUILIBRADO -> {
                return random.nextInt(3)+1;
            }
            case DEFENSIVO -> {
                return random.nextInt(2);
            }
            default -> {
                return random.nextInt(1);
            }
        }
    }
}
