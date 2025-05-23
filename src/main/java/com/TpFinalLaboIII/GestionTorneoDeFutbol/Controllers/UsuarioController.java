package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Usuario;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private final ServicesUser servicesUser;

    public UsuarioController(ServicesUser servicesUser) {
        this.servicesUser = servicesUser;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> getUser(@PathVariable long id) {
        Optional<Usuario> usuario = servicesUser.getUsersByID(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
