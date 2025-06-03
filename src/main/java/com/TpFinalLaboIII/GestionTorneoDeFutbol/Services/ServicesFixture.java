package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.FixtureDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Fixture;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOPARTIDO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOTORNEO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryFixture;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicesFixture {
    @Autowired
    private final IRepositoryFixture iRepositoryFixture;
    @Autowired
    private final ServicesTorneo servicesTorneo;


    public ServicesFixture(IRepositoryFixture iRepositoryFixture, ServicesTorneo servicesTorneo) {
        this.iRepositoryFixture = iRepositoryFixture;
        this.servicesTorneo = servicesTorneo;
    }

    public ResponseEntity<String>gerenateFixture(@PathVariable long idTorneo) throws NotFoundException, NotPostException
    {
        Torneo torneo = servicesTorneo.torneoExistAndPresent(idTorneo);
        if(torneo == null)
        {
            throw new NotFoundException("No existe torneo con ese ID para desarrollar el fixture");
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
                fixtureIda.setGolesEquipo1(0);
                fixtureIda.setGolesEquipo2(0);
                fixtureIda.setNombreTorneo(torneo);
                fixtures.add(fixtureIda);


                Fixture fixtureVuelta = new Fixture();
                fixtureVuelta.setLocal(equipo2);
                fixtureVuelta.setVisitante(equipo1);
                fixtureVuelta.setFechaPartido(fechaBase.plusDays(contadorDeDias++ * diasEntrePartidos));
                fixtureVuelta.setEstadopartido(ESTADOPARTIDO.PENDIENTE);
                fixtureVuelta.setGolesEquipo1(0);
                fixtureVuelta.setGolesEquipo2(0);
                fixtureVuelta.setNombreTorneo(torneo);
                fixtures.add(fixtureVuelta);
            }
        }

        iRepositoryFixture.saveAll(fixtures);
        return ResponseEntity.ok("Fixture IDA y Vuelta generados");
    }


}
