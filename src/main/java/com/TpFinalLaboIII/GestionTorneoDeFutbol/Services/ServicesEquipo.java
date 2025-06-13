package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTOconDtDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.JugadorDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.TorneoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.*;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOTORNEO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryDt;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryPlayer;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryTeam;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryTournaumet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesEquipo {

    @Autowired
    private final IRepositoryTeam iRepositoryTeam;
    @Autowired
    private final ServicesDt servicesDt;
    @Autowired
    private final ServicesTorneo servicesTorneo;
    @Autowired
    private final IRepositoryPlayer iRepositoryPlayer;
    @Autowired
    private final IRepositoryTournaumet iRepositoryTournaumet;
    @Autowired
    private final ServicesUser servicesUser;

    public ServicesEquipo(IRepositoryTeam iRepositoryTeam, ServicesDt servicesDt, ServicesTorneo servicesTorneo, IRepositoryPlayer iRepositoryPlayer, IRepositoryTournaumet iRepositoryTournaumet, ServicesUser servicesUser) {
        this.iRepositoryTeam = iRepositoryTeam;
        this.servicesDt = servicesDt;
        this.servicesTorneo = servicesTorneo;
        this.iRepositoryPlayer = iRepositoryPlayer;
        this.iRepositoryTournaumet = iRepositoryTournaumet;
        this.servicesUser = servicesUser;
    }

    public ResponseEntity<String> createDtAndTeam(@RequestBody EquipoDTOconDtDTO equipoDTOconDtDTO, @PathVariable long idTorneo) throws NotFoundException, NotPostException
    {
        if(equipoDTOconDtDTO.getEquipoDTO().getNombreEquipo() == null || equipoDTOconDtDTO.getEstilodejuegoDT() == null)
        {
            throw new NotPostException("Error en los de EQUIPODTO, ESTILO DE JUEGO - NOMBRE EQUIPO");
        }

        Boolean torneoExists = servicesTorneo.torneoExists(idTorneo);
        if(!torneoExists)
        {
            throw new NotFoundException("El ID no corresponde a un torneo existente y no puede agregar equipos");
        }

        Torneo torneo = servicesTorneo.torneoExistAndPresent(idTorneo);
        if(torneo.getEquipos() != null && torneo.getEquipos().size()>=4)
        {
            throw new NotPostException("Ya hay 4 equipos en el torneo");
        }

        Optional<Usuario> usuarioDT = servicesUser.getUsuarioOptional(equipoDTOconDtDTO.getIdDT());
        DT nuevoDt = servicesDt.createDT(usuarioDT.get(), equipoDTOconDtDTO);

        Equipo nuevoEquipo = new Equipo();
        nuevoEquipo.setNombre(equipoDTOconDtDTO.getEquipoDTO().getNombreEquipo());

        nuevoEquipo.setDt(nuevoDt);
        nuevoEquipo.setNombreTorneo(torneo);

        iRepositoryTeam.save(nuevoEquipo);

        torneo.getEquipos().add(nuevoEquipo); // actualiza la lista en memoria
        if(torneo.getEquipos().size() == 4 && torneo.getEstadotorneo() == ESTADOTORNEO.PENDIENTE)
        {
            torneo.setEstadotorneo(ESTADOTORNEO.ESPERA);
            iRepositoryTournaumet.save(torneo);
        }

        return ResponseEntity.ok("Equipo creado correctamente con su respectivo DT y torneo al que pertenece");
    }

    public List<EquipoDTO>getListTeam() throws NotFoundException
    {
        List<Equipo> equipos = iRepositoryTeam.findAll();
        if(equipos.isEmpty())
        {
            throw new NotFoundException("No se encuentran equipos cargados en la base de datos del torneo");
        }

        List<EquipoDTO>equiposDTO = new ArrayList<>();
        for(Equipo e: equipos)
        {
            EquipoDTO equipoDTO = new EquipoDTO();
            equipoDTO.setIdEquipo(e.getIdEquipo());
            equipoDTO.setNombreEquipo(e.getNombre());
            equipoDTO.setNombreTorneo(e.getNombreTorneo().getNombre());
            equipoDTO.setIdTorneo(e.getNombreTorneo().getIdTorneo());
            equiposDTO.add(equipoDTO);
        }
        return equiposDTO;
    }

    public ResponseEntity<String>updateDtTeam(@PathVariable Long idEquipo, @PathVariable long idDt) throws NotFoundException
    {
        Equipo equipo = iRepositoryTeam.findById(idEquipo)
                .orElseThrow(() -> new NotFoundException("Error, ID DE EQUIPO INEXISTENTE"));
        DT dt = servicesDt.getDt(idDt);

        Optional<Equipo> equipoConDT= iRepositoryTeam.findByDt(dt);
        if(equipoConDT.isPresent())
        {
            Equipo equipoExistente = equipoConDT.get();
            equipoExistente.setDt(null);
            iRepositoryTeam.save(equipoExistente);
        }
        equipo.setDt(dt);
        iRepositoryTeam.save(equipo);

        return ResponseEntity.ok("Datos del equipo:" + idEquipo + "DT ACTUALIZADO");
    }

    public ResponseEntity<String>DeleteTeam(@PathVariable long idEquipo) throws NotFoundException
    {
        Equipo equipo = iRepositoryTeam.findById(idEquipo).orElseThrow(() -> new NotFoundException("Error el equipo no se encuenta en la base de datos"));
        equipo.setDt(null);
        for(Jugador j: equipo.getJugadores())
        {
            iRepositoryPlayer.delete(j);
        }
        iRepositoryTeam.delete(equipo);
        return ResponseEntity.ok("Equipo eliminado de la base de datos.");
    }


    public List<JugadorDTO> getListTeamAll(@PathVariable long idEquipo) throws NotFoundException
    {
        List<JugadorDTO>listAllEquipoDTO = new ArrayList<>();

        Equipo equipo = iRepositoryTeam.findByIdEquipo(idEquipo).orElseThrow(() -> new NotFoundException("Error, el ID del equipo no se encuenta en la Base de datos"));
        for(Jugador j : equipo.getJugadores())
        {
            JugadorDTO nuevoJDto = new JugadorDTO();
            nuevoJDto.setNombre(j.getNombre());
            nuevoJDto.setPosicion(j.getPosicion());
            nuevoJDto.setIdEquipo(j.getEquipo().getIdEquipo());
            nuevoJDto.setIdJugador(j.getIdJugador());
            nuevoJDto.setNumeroCamiseta(j.getNumeroCamiseta());
            listAllEquipoDTO.add(nuevoJDto);
        }

        if(listAllEquipoDTO.isEmpty())
        {
            throw new NotFoundException("No se encuentan jugadores asociados al equipo:" + equipo.getNombre());
        }

        return listAllEquipoDTO;
    }

    //OPTIONALS
    public Optional<Equipo>teamByID(Long idEquipo)
    {
        Optional<Equipo>equipoById= iRepositoryTeam.findByIdEquipo(idEquipo);
        return equipoById;
    }
}
