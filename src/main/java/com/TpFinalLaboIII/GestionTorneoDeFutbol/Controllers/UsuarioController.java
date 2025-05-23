package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private final ServicesUser servicesUser;

    public UsuarioController(ServicesUser servicesUser) {
        this.servicesUser = servicesUser;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void>getUser(@RequestParam long id)
    {
        servicesUser.getUsersByID(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
