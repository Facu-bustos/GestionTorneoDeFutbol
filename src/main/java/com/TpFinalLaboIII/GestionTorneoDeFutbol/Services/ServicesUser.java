package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.UserDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.ViewUserDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Fixture;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Usuario;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ESTADOTORNEO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryTournaumet;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryUser;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.View;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesUser {
    @Autowired
    private final IRepositoryUser iRepositoryUser;
    @Autowired
    private final IRepositoryTournaumet iRepositoryTournaumet;

    public ServicesUser(IRepositoryUser iRepositoryUser, IRepositoryTournaumet iRepositoryTournaumet) {
        this.iRepositoryUser = iRepositoryUser;
        this.iRepositoryTournaumet = iRepositoryTournaumet;
    }


    //  METODOS

    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) throws NotPostException {
        boolean emailPresent = getUserByEmail(userDTO.getEmail());
        if (emailPresent) {
            throw new NotPostException("Error, el email se encuentra presente en la BDD");
        }

        if (userDTO.getUsername() == null || userDTO.getPassword() == null
                || userDTO.getEmail() == null || userDTO.getRoleuser() != ROLEUSER.ADMINISTRADOR) {
            throw new NotPostException("Error al ingresar un ADMIN, datos erroneos");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(userDTO.getUsername());
        usuario.setEmail(userDTO.getEmail());
        usuario.setPassword(userDTO.getPassword());
        usuario.setRoleuser(userDTO.getRoleuser());
        iRepositoryUser.save(usuario);
        return ResponseEntity.ok("Usuario creado con exito");
    }

    public Boolean getUserByEmail(@PathVariable String email) {
        Optional<Usuario> emailUsuario = iRepositoryUser.findByEmail(email);
        if (emailUsuario.isPresent()) {
            return true;
        }
        return false;
    }

    public ViewUserDTO getUsersByID(@PathVariable long id) throws NotFoundException {
        Optional<Usuario> userByID = iRepositoryUser.findById(id);
        if (userByID.isEmpty()) {
            throw new NotFoundException("No se encuentra el ID solicitado");
        }
        ViewUserDTO userDTO = new ViewUserDTO();
        userDTO.setIdUsuario(userByID.get().getIdUsuario());
        userDTO.setUsername(userByID.get().getUsername());
        userDTO.setRoleuser(userByID.get().getRoleuser());
        userDTO.setEmail(userByID.get().getEmail());
        return userDTO;
    }


    public ResponseEntity<String> deleteUser(@PathVariable long id) throws NotFoundException {
        Optional<Usuario> user = iRepositoryUser.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Error, ID no encontrado");
        }
        iRepositoryUser.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado con exito");
    }

    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) throws NotFoundException {
        Usuario userById = iRepositoryUser.findById(id)
                .orElseThrow(() -> new NotFoundException("Error, ID no encontrado"));

        userById.setUsername(userDTO.getUsername());
        userById.setPassword(userDTO.getPassword());
        userById.setEmail(userDTO.getEmail());
        userById.setRoleuser(userDTO.getRoleuser());

        iRepositoryUser.save(userById);
        return ResponseEntity.ok("Usuario ID " + id + " ACTUALIZADO");
    }

    public List<ViewUserDTO> listUser() throws NotFoundException {
        List<Usuario> listUser = iRepositoryUser.findAll();
        if (listUser.isEmpty()) {
            throw new NotFoundException("No se encuentra la lista de usuarios disponible");
        }

        List<ViewUserDTO> listUserDTOS = new ArrayList<>();

        for (Usuario u : listUser) {
            ViewUserDTO viewUserDTO = new ViewUserDTO();
            viewUserDTO.setIdUsuario(u.getIdUsuario());
            viewUserDTO.setUsername(u.getUsername());
            viewUserDTO.setEmail(u.getEmail());
            viewUserDTO.setRoleuser(u.getRoleuser());
            listUserDTOS.add(viewUserDTO);
        }
        return listUserDTOS;
    }

    public ViewUserDTO getUserByEmailPost(@RequestParam String email) throws NotFoundException {
        Usuario userByEmail = iRepositoryUser.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Error, email no encontrado en la BDD"));

        ViewUserDTO viewUserDTO = new ViewUserDTO();
        viewUserDTO.setIdUsuario(userByEmail.getIdUsuario());
        viewUserDTO.setUsername(userByEmail.getUsername());
        viewUserDTO.setEmail(userByEmail.getEmail());
        viewUserDTO.setRoleuser(userByEmail.getRoleuser());
        return viewUserDTO;
    }

    public ResponseEntity<String>addTorneo(@Valid @RequestBody Torneo torneo) throws NotPostException {

        LocalDateTime day = LocalDateTime.now();
        if(torneo.getNombre() == null || torneo.getFechaInicio().isBefore(day) || torneo.getEstadotorneo() != ESTADOTORNEO.PENDIENTE)
        {
            throw new NotPostException("Error en los datos del torneo. No se puede dar de alta");
        }

        Torneo torneo1 = new Torneo();
        List<Fixture>fixture = new ArrayList<>();
        List<Equipo>equipolist = new ArrayList<>();
        torneo1.setNombre(torneo.getNombre());
        torneo1.setFechaInicio(torneo.getFechaInicio());
        torneo1.setFechaFin(torneo.getFechaFin());
        torneo1.setEstadotorneo(ESTADOTORNEO.PENDIENTE);
        torneo1.setEquipos(equipolist);
        torneo1.setFixture(fixture);
        iRepositoryTournaumet.save(torneo1);
        return ResponseEntity.ok("Torneo " + torneo.getNombre() + " CREADO CON EXITO" );
    }





}
