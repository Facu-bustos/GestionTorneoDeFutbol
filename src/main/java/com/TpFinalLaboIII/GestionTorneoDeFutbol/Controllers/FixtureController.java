package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.FixtureDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.FixtureDTOUpdate;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.FixtureDTOView;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.FixtureDTOViewEnd;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesFixture;
import org.aspectj.weaver.ast.Not;
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

    @PostMapping("/generate/result/{idTorneo}")
    public ResponseEntity<String>generateFixtureResult(@PathVariable long idTorneo) throws NotFoundException, NotPostException
    {
        return servicesFixture.generateFixtureResult(idTorneo);
    }

    @GetMapping("/get/fixture/{idTorneo}")
    @ResponseBody
    public List<FixtureDTOView>getFixtureDTO(@PathVariable long idTorneo) throws NotFoundException
    {
        return servicesFixture.getAllFixture(idTorneo);
    }

    @GetMapping("/get/fixture/end/{idTorneo}")
    @ResponseBody
    public List<FixtureDTOViewEnd>getFixtureDtoEnd(@PathVariable long idTorneo) throws NotFoundException
    {
        return servicesFixture.getFixtureDtoEnd(idTorneo);
    }

    @PutMapping("/update/fixture")
    public ResponseEntity<String>updateFixture(@RequestBody FixtureDTOUpdate fx) throws NotFoundException, NotPostException
    {
        return servicesFixture.updateFixtureExists(fx);
    }

    @DeleteMapping("/delete/fixture/{idFixture}")
    public ResponseEntity<String>deleteFixtureID(@PathVariable long idFixture) throws NotFoundException
    {
        return servicesFixture.deleteFixtureId(idFixture);
    }

    @DeleteMapping("/deleteAll/fixture/{idTorneo}")
    public ResponseEntity<String>deleteFixtureAllTournament(@PathVariable long idTorneo) throws NotFoundException
    {
        return servicesFixture.deleteFixtureAllTournament(idTorneo);
    }

}
