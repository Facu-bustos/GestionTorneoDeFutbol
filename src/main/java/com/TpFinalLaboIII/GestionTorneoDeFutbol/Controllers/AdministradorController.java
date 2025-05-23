package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.AdmDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesADM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private final ServicesADM servicesADM;

    public AdministradorController(ServicesADM servicesADM) {
        this.servicesADM = servicesADM;
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<Void>postAdmin(@RequestBody AdmDTO admdto)
    {
        servicesADM.postAdmin(admdto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getAdmin/{id}")
    public ResponseEntity<Void>getAdmin(@RequestParam Long id)
    {
        servicesADM.getAdmin(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
