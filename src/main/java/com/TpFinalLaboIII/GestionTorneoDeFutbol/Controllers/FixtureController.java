package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.FixtureDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.FixtureDTOView;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fixture")
public class FixtureController {

    @Autowired
    private final ServicesFixture servicesFixture;

    public FixtureController(ServicesFixture servicesFixture) {
        this.servicesFixture = servicesFixture;
    }


    @PostMapping("/generate/{idTorneo}")
    public ResponseEntity<String>gerenateFixture(@PathVariable long idTorneo) throws NotFoundException, NotPostException
    {
        return servicesFixture.gerenateFixture(idTorneo);
    }

    @GetMapping("/get/fixture/{idTorneo}")
    @ResponseBody
    public List<FixtureDTOView>getFixtureDTO(@PathVariable long idTorneo) throws NotFoundException
    {
        return servicesFixture.getAllFixture(idTorneo);
    }



}
