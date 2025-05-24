package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.UserDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Usuario;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class ServicesUser {
    @Autowired
    private final IRepositoryUser iRepositoryUser;

    public ServicesUser(IRepositoryUser iRepositoryUser) {
        this.iRepositoryUser = iRepositoryUser;
    }


    //  METODOS

    public ResponseEntity<UserDTO>addUser(@RequestBody UserDTO userDTO) throws NotPostException
    {
        boolean emailPresent = getUserByEmail(userDTO.getEmail());
        if(emailPresent)
        {
            throw new NotPostException("Error, el email se encuentra presente en la BDD");
        }

        if(userDTO.getUsername()==null || userDTO.getPassword()== null
                || userDTO.getEmail() == null || userDTO.getRoleuser() == null)
        {
            throw new NotPostException("Error al ingresar un ADMIN, datos erroneos");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(userDTO.getUsername());
        usuario.setEmail(userDTO.getEmail());
        usuario.setPassword(userDTO.getPassword());
        usuario.setRoleuser(userDTO.getRoleuser());
        iRepositoryUser.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public Boolean getUserByEmail(@PathVariable String email)
    {
        Optional<Usuario>emailUsuario=iRepositoryUser.findByEmail(email);
        if(emailUsuario.isPresent())
        {
            return true;
        }
        return false;
    }

    public UserDTO getUsersByID(@PathVariable long id) throws NotFoundException
    {
        Optional<Usuario>userByID=iRepositoryUser.findById(id);
        if(userByID.isEmpty())
        {
            throw new NotFoundException("No se encuentra el ID solicitado");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUsuario(userByID.get().getIdUsuario());
        userDTO.setUsername(userByID.get().getUsername());
        userDTO.setRoleuser(userByID.get().getRoleuser());
        userDTO.setEmail(userByID.get().getEmail());
        userDTO.setPassword("NO DISPONIBLE");
        return userDTO;
    }



    public ResponseEntity<Void> deleteUser(@PathVariable long id) throws NotFoundException
    {
        Optional<Usuario>user = iRepositoryUser.findById(id);
        if(user.isEmpty())
        {
            throw new NotFoundException("Error, ID no encontrado");
        }
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(user.get().getIdUsuario());
        usuario.setUsername(user.get().getUsername());
        usuario.setRoleuser(user.get().getRoleuser());
        usuario.setPassword(user.get().getPassword());
        usuario.setEmail(user.get().getEmail());
        iRepositoryUser.delete(usuario);
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }



}
