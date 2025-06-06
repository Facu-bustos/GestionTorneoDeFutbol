package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.EquipoDTOconDtDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.DT;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories.IRepositoryDt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.plaf.PanelUI;
import java.util.Optional;

@Service
public class ServicesDt {
    @Autowired
    private final IRepositoryDt iRepositoryDt;

    public ServicesDt(IRepositoryDt iRepositoryDt) {
        this.iRepositoryDt = iRepositoryDt;
    }

    public DT createDT(@RequestBody EquipoDTOconDtDTO dtDto) throws NotPostException
    {
     if(dtDto.getNombreDT()==null || dtDto.getEstilodejuegoDT() == null || dtDto.getRoleuserDT() != ROLEUSER.DT)
     {
         throw new NotPostException("Error a la carga del dt. Errores en Nombre o Estilo de juego o Role");
     }

     DT nuevoDT = new DT();
     nuevoDT.setNombre(dtDto.getNombreDT());
     nuevoDT.setEstilodejuego(dtDto.getEstilodejuegoDT());
     nuevoDT.setRoleuser(dtDto.getRoleuserDT());
     iRepositoryDt.save(nuevoDT);

     return nuevoDT;
    }

    public DT getDt(@PathVariable Long idDT) throws NotFoundException
    {
        DT dt = iRepositoryDt.findById(idDT).orElseThrow(() -> new NotFoundException("DT NO EXISTE EN LA BASE DE DATOS"));
        return dt;
    }



}
