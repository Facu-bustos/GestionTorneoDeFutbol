package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTOconDtDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.TorneoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.DT;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryDt;
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

    public ServicesEquipo(IRepositoryTeam iRepositoryTeam, ServicesDt servicesDt, ServicesTorneo servicesTorneo) {
        this.iRepositoryTeam = iRepositoryTeam;
        this.servicesDt = servicesDt;
        this.servicesTorneo = servicesTorneo;
    }

    public ResponseEntity<String> createDtAndTeam(@RequestBody EquipoDTOconDtDTO equipoDTOconDtDTO, @PathVariable long idTorneo) throws NotFoundException, NotPostException
    {
        if(equipoDTOconDtDTO.getEquipoDTO().getNombreEquipo() == null || equipoDTOconDtDTO.getEstilodejuegoDT() == null || equipoDTOconDtDTO.getRoleuserDT() != ROLEUSER.DT || equipoDTOconDtDTO.getNombreDT() == null)
        {
            throw new NotPostException("Error en los de EQUIPODTO, NOMBRES - ESTILO DE JUEGO - ROLE");
        }

        Boolean torneoExists = servicesTorneo.torneoExists(idTorneo);
        if(!torneoExists)
        {
            throw new NotFoundException("El ID no corresponde a un torneo existente");
        }

        Torneo torneo = servicesTorneo.torneoExistAndPresent(idTorneo);
        DT nuevoDt = servicesDt.createDT(equipoDTOconDtDTO);

        Equipo nuevoEquipo = new Equipo();
        nuevoEquipo.setNombre(equipoDTOconDtDTO.getEquipoDTO().getNombreEquipo());
        nuevoEquipo.setDt(nuevoDt);
        nuevoEquipo.setNombreTorneo(torneo);

        iRepositoryTeam.save(nuevoEquipo);
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
            equiposDTO.add(equipoDTO);
        }
        return equiposDTO;
    }

   /* public ResponseEntity<String>updateTeamDTO(@RequestBody EquipoDTOconDtDTO equipoDTO, @PathVariable long idEquipo) throws NotFoundException
    {
        Equipo equipo = iRepositoryTeam.findById(idEquipo)
                .orElseThrow(() -> new NotFoundException("Error, ID DE EQUIPO INEXISTENTE"));

        equipo.setNombre(equipoDTO.getEquipoDTO().getNombreEquipo());


                // private long idDT;
        //    private String nombreDT;
        //    private ESTILODEJUEGO estilodejuegoDT;
        //    private ROLEUSER roleuserDT ;
        //    private EquipoDTO equipoDTO;

        return ResponseEntity.ok("Datos del equipo:" + idEquipo + " Actualizados");
    }
*/
}
