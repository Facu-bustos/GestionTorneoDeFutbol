package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.TorneoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.DT;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOTORNEO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryTournaumet;
import jakarta.validation.Valid;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesTorneo  {

    @Autowired
    private final IRepositoryTournaumet iRepositoryTournaumet;


    public ServicesTorneo(IRepositoryTournaumet iRepositoryTournaumet) {
        this.iRepositoryTournaumet = iRepositoryTournaumet;
    }


    public ResponseEntity<String> addTorneo(@Valid @RequestBody TorneoDTO torneo) throws NotPostException {

        LocalDateTime day = LocalDateTime.now();
        if(torneo.getNombre() == null || torneo.getFechaInicio().isBefore(day))
        {
            throw new NotPostException("Error en los datos del torneo. No se puede dar de alta");
        }

        Torneo torneo1 = new Torneo();
        torneo1.setNombre(torneo.getNombre());
        torneo1.setFechaInicio(torneo.getFechaInicio());
        torneo1.setFechaFin(torneo.getFechaFin());
        torneo1.setEstadotorneo(ESTADOTORNEO.PENDIENTE);
        iRepositoryTournaumet.save(torneo1);
        return ResponseEntity.ok("Torneo " + torneo.getNombre() + " CREADO CON EXITO" );
    }

    public TorneoDTO getTorneoById(@PathVariable long id) throws NotFoundException
    {
        Torneo torneo = iRepositoryTournaumet.findById(id).orElseThrow(() -> new NotFoundException("Error, el ID del torneo no se encuenta en la BDD"));

        TorneoDTO torneoDTO = new TorneoDTO();
        torneoDTO.setNombre(torneo.getNombre());
        torneoDTO.setIdTorneo(torneo.getIdTorneo());
        torneoDTO.setEstadotorneo(torneo.getEstadotorneo());
        torneoDTO.setFechaInicio(torneo.getFechaInicio());
        torneoDTO.setFechaFin(torneo.getFechaFin());
        return torneoDTO;
    }

   public Torneo torneoExistAndPresent(@PathVariable long id) throws  NotFoundException
   {
       Torneo torneo = iRepositoryTournaumet.findById(id).orElseThrow(() -> new NotFoundException("Error, el ID del torneo no se encuenta en la BDD"));
       return torneo;
   }


    public boolean torneoExists(@PathVariable long id)
    {
       Optional<Torneo> torneoExists = iRepositoryTournaumet.findById(id);
       if(torneoExists.isPresent())
       {
           return true;
       }
       return false;
    }

    public ResponseEntity<String> deleteTorneoById(@PathVariable long id) throws NotFoundException
    {
        Optional<Torneo> torneo = iRepositoryTournaumet.findById(id);
        if(torneo.isEmpty())
        {
            throw new NotFoundException("Error, el ID del torneo no se encuenta en la BDD");
        }

        iRepositoryTournaumet.deleteById(id);
        return ResponseEntity.ok("Torneo ID: " + id + " Eliminado con exito");
    }

    public ResponseEntity<String>updateTorneoById(@PathVariable long id, @RequestBody TorneoDTO torneoDTO) throws NotFoundException
    {
     Optional<Torneo>torneo = iRepositoryTournaumet.findById(id);

     if(torneo.isEmpty())
     {
         throw new NotFoundException("Error, el ID del torneo no se encuenta en la BDD");
     }

     LocalDateTime day = LocalDateTime.now();
     if(torneoDTO.getFechaInicio().isBefore(day))
     {
         throw new NotFoundException("Error, la fecha de iniciacion del partido no puede ser menor a hoy");
     }

     torneo.get().setNombre(torneoDTO.getNombre());
     torneo.get().setFechaInicio(torneoDTO.getFechaInicio());
     torneo.get().setFechaFin(torneoDTO.getFechaFin());
     iRepositoryTournaumet.save(torneo.get());
     return ResponseEntity.ok("Torneo ID: " + id + " Actualizado con exito");
    }

    public List<TorneoDTO>getListTorneo() throws NotFoundException {


        List<Torneo>torneos = iRepositoryTournaumet.findAll();
        if(torneos.isEmpty())
        {
            throw new NotFoundException("Error, no se encuentran Torneos Disponibles.. Cargue un torneo");
        }

        List<TorneoDTO>listaDeTorneosDTO = new ArrayList<>();
        for(Torneo t: torneos)
        {
            TorneoDTO torneoDTO = new TorneoDTO();
            torneoDTO.setIdTorneo(t.getIdTorneo());
            torneoDTO.setNombre(t.getNombre());
            torneoDTO.setFechaInicio(t.getFechaInicio());
            torneoDTO.setFechaFin(t.getFechaFin());
            torneoDTO.setEstadotorneo(t.getEstadotorneo());
            listaDeTorneosDTO.add(torneoDTO);
        }
        return listaDeTorneosDTO;
    }


}
