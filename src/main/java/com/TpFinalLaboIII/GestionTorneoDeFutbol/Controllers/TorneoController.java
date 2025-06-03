package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.TorneoDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesTorneo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournament")
public class TorneoController {

    @Autowired
    private final ServicesTorneo servicesTorneo;

    public TorneoController(ServicesTorneo servicesTorneo) {
        this.servicesTorneo = servicesTorneo;
    }


    //ENDPOINTS DE TORNEO
    @PostMapping("/addTournament")
    public ResponseEntity<String> addTorneo(@Valid @RequestBody TorneoDTO torneo) throws NotPostException
    {
        return servicesTorneo.addTorneo(torneo);
    }

    @GetMapping("{id}")
    @ResponseBody
    public TorneoDTO getTorneo(@PathVariable long id) throws NotFoundException
    {
        return servicesTorneo.getTorneoById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteTorneo(@PathVariable long id) throws NotFoundException
    {
        return servicesTorneo.deleteTorneoById(id);
    }

    @PutMapping("/updateTournament/{id}")
    @ResponseBody
    public ResponseEntity<String>UpdateTorneo(@PathVariable long id, @RequestBody TorneoDTO torneoDTO) throws NotFoundException
    {
        return servicesTorneo.updateTorneoById(id,torneoDTO);
    }

   //getlistarTorneos
    @GetMapping("/getListTorneo")
    @ResponseBody
    public List<TorneoDTO> getListTeam()throws NotFoundException
    {
        return servicesTorneo.getListTorneo();
    }




}
