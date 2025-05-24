package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.UserDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
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

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO>addUser(@RequestBody UserDTO userDTO) throws NotPostException
    {
        return  servicesUser.addUser(userDTO);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserDTO getUser(@PathVariable long id) throws NotFoundException {
        return servicesUser.getUsersByID(id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable long id) throws NotFoundException
    {
       return servicesUser.deleteUser(id);
    }



}
