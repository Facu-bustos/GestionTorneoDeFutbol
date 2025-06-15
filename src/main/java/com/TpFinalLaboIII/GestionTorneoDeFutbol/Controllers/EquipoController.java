package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTOconDtDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.JugadorDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesEquipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesJugador;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class EquipoController {
    @Autowired
    private final ServicesEquipo servicesEquipo;

    public EquipoController(ServicesEquipo servicesEquipo) {
        this.servicesEquipo = servicesEquipo;
    }

    @PostMapping("/addTeam/{idTorneo}")
    public ResponseEntity<String>addTeam(@RequestBody EquipoDTOconDtDTO equipoDTO, @PathVariable long idTorneo) throws NotPostException, NotFoundException
    {
        return servicesEquipo.createDtAndTeam(equipoDTO,idTorneo);
    }

    @GetMapping("/listTeam")
    @ResponseBody
    public List<EquipoDTO> getListTeamDTO() throws NotFoundException
    {
        return servicesEquipo.getListTeam();
    }

    @DeleteMapping("/deleteTeam/{idEquipo}")
    public ResponseEntity<String>DeleteTeam(@PathVariable long idEquipo) throws NotFoundException
    {
        return servicesEquipo.DeleteTeam(idEquipo);
    }

    // Actualizacion de DT EQUIPO
    @PutMapping("/updateDtTeam/{idEquipo}/assingnamentDT/{idDt}")
    public ResponseEntity<String>updateEquipoDT(@PathVariable Long idEquipo, @PathVariable long idDt) throws NotFoundException
    {
        return servicesEquipo.updateDtTeam(idEquipo,idDt);
    }

    @GetMapping("/getListTeamPlayerByID/{idEquipo}")
    @ResponseBody
    public List<JugadorDTO> getListTeamAll(@PathVariable long idEquipo) throws NotFoundException
    {
        return servicesEquipo.getListTeamAll(idEquipo);
    }

}
