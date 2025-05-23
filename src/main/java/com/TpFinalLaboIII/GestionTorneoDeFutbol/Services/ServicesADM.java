package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.AdmDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Administrador;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryADM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class ServicesADM {

    @Autowired
    private final IRepositoryADM iRepositoryADM;

    public ServicesADM(IRepositoryADM iRepositoryADM) {
        this.iRepositoryADM = iRepositoryADM;
    }


    public ResponseEntity<Void> postAdmin(@RequestBody AdmDTO adminDTO)
    {
        Administrador admin = new Administrador();
        admin.setUsername(adminDTO.getUsername());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
        admin.setRoleuser(adminDTO.getRoleuser());
        if(admin==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            iRepositoryADM.save(admin);
        }
        return ResponseEntity.ok().build();
    }
}
