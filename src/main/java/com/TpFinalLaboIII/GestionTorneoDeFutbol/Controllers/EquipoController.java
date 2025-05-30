package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTOconDtDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesEquipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesJugador;
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
  /*
    @PutMapping("{idEquipo}")
    public ResponseEntity<String>updateEquipo(@RequestBody EquipoDTOconDtDTO equipoDTO)
    {
        return servicesEquipo.updateTeamDTO();
    }
*/
}
