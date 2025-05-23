package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Usuario;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class ServicesUser {


    @Autowired
    private final IRepositoryUser iRepositoryUser;


    public ServicesUser(IRepositoryUser iRepositoryUser) {
        this.iRepositoryUser = iRepositoryUser;
    }

    public Optional<Usuario> getUsersByID(@PathVariable long id)
    {
        Optional<Usuario>userByID=iRepositoryUser.findById(id);
        return userByID;
    }

}
