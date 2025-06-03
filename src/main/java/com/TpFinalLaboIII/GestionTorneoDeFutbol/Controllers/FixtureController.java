package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
