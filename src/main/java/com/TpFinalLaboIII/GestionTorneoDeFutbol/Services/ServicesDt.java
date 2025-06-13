package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTOconDtDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.ViewUserDTDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.ViewUserDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.DT;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Jugador;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Usuario;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryDt;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesDt {
    @Autowired
    private final IRepositoryDt iRepositoryDt;
    @Autowired
    private final IRepositoryPlayer iRepositoryPlayer;
    @Autowired
    private final ServicesUser servicesUser;

    public ServicesDt(IRepositoryDt iRepositoryDt, IRepositoryPlayer iRepositoryPlayer, ServicesUser servicesUser) {
        this.iRepositoryDt = iRepositoryDt;
        this.iRepositoryPlayer = iRepositoryPlayer;
        this.servicesUser = servicesUser;
    }

    public DT createDT(@RequestBody Usuario usuario, @RequestBody EquipoDTOconDtDTO equipoDTOconDtDTO) throws NotPostException
    {
     if(usuario.getUsername() == null || usuario.getRoleuser() != ROLEUSER.DT || usuario.getIdUsuario() == null)
     {
         throw new NotPostException("Error a la carga del dt. Errores en Nombre o Estilo de juego o Role");
     }

     if(usuario.getDt() != null)
     {
         return usuario.getDt();
     }

     DT nuevoDT = new DT();
     nuevoDT.setNombre(usuario.getUsername());
     nuevoDT.setEstilodejuego(equipoDTOconDtDTO.getEstilodejuegoDT());
     iRepositoryDt.save(nuevoDT);

     return nuevoDT;
    }

    public DT getDt(@PathVariable Long idDT) throws NotFoundException
    {
        DT dt = iRepositoryDt.findById(idDT).orElseThrow(() -> new NotFoundException("DT NO EXISTE EN LA BASE DE DATOS"));
        return dt;
    }


    public void deletePlayerById(long jugadorId, String emailDt) throws  NotFoundException, NotPostException {
        DT dt = iRepositoryDt.findByUsuarioEmail(emailDt)
                .orElseThrow(() -> new NotFoundException("DT no encontrado"));

        Jugador jugador = iRepositoryPlayer.findById(jugadorId)
                .orElseThrow(() -> new NotFoundException("Jugador no encontrado"));

        if (jugador.getEquipo().getIdEquipo() != dt.getEquipo().getIdEquipo()) {
            throw new NotPostException("No podes eliminar un jugador que no es de tu equipo");
        }

        iRepositoryPlayer.delete(jugador);
    }

    public List<ViewUserDTDTO>listAllDts() throws NotFoundException {
        List<ViewUserDTO>dts = servicesUser.listUser();

        List<ViewUserDTDTO>viewsUserDTOS = new ArrayList<>();



        for(ViewUserDTO dt: dts)
        {
           if(dt.getRoleuser() == ROLEUSER.DT)
           {
               ViewUserDTDTO vw = new ViewUserDTDTO();
               vw.setNombre(dt.getUsername());
               vw.setEmail(dt.getEmail());
               vw.setRoleuser(dt.getRoleuser());
               viewsUserDTOS.add(vw);
           }
        }

        if(viewsUserDTOS.isEmpty())
        {
            throw new NotFoundException("Error");
        }

        return  viewsUserDTOS;
    }


}

