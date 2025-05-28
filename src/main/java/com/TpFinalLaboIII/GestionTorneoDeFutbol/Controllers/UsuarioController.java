package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.UserDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.ViewUserDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.DT;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Usuario;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesTorneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    private final ServicesUser servicesUser;

    public UsuarioController(ServicesUser servicesUser) {
        this.servicesUser = servicesUser;
    }


    //ENDPOINTS DE USUARIO

    @PostMapping("/addUser")
    public ResponseEntity<String>addUser(@RequestBody UserDTO userDTO) throws NotPostException
    {
        return  servicesUser.addUser(userDTO);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ViewUserDTO getUser(@PathVariable long id) throws NotFoundException {
        return servicesUser.getUsersByID(id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable long id) throws NotFoundException
    {
       return servicesUser.deleteUser(id);
    }

    @PutMapping("/updateUser/{id}")
    @ResponseBody
    public ResponseEntity<String>updateUser(@PathVariable long id, @RequestBody  UserDTO userDTO) throws NotFoundException
    {
        return servicesUser.updateUser(id, userDTO);
    }

    @GetMapping("/listUser")
    @ResponseBody
    public List<ViewUserDTO>listViewUserDTO() throws NotFoundException
    {
        return servicesUser.listUser();
    }

    @GetMapping("/getUserByEmail")
    @ResponseBody
    public ViewUserDTO getUserByEmailPost(@RequestParam String email) throws NotFoundException
    {
        return servicesUser.getUserByEmailPost(email);
    }
}
