package com.TpFinalLaboIII.GestionTorneoDeFutbol.Services;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.DT;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Enums.ROLEUSER;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class ServicesEquipo {






/*
    public ResponseEntity<String> addDtAndTeam(@RequestBody Equipo equipo, @RequestBody DT dt, @PathVariable long id) throws NotFoundException, NotPostException
    {

        if(dt.getNombre()== null || dt.getEstilodejuego() == null || dt.getRoleuser() != ROLEUSER.DT)
        {
            throw new NotPostException("Error en los datos del DT, NOMBRE - ESTILO DE JUEGO - ROLE");
        }

        if(equipo.getNombre()== null)
        {
            throw new NotPostException("Error en los datos del equipo. Falta un nombre");
        }

        Torneo torneo = iRepositoryTournaumet.findById(id).orElseThrow(() -> new NotFoundException("Error, id de torneo inexistente"));


        DT nuevoDT = new DT();
        nuevoDT.setNombre(dt.getNombre());
        nuevoDT.setRoleuser(dt.getRoleuser());
        nuevoDT.setEstilodejuego(dt.getEstilodejuego());

        Equipo equipo1 = new Equipo();
        equipo1.setNombre(equipo.getNombre());
        equipo1.setNombreTorneo(torneo);

        return ResponseEntity.ok("");

        //falta temrinar la logica. CREAER DT VERIFICAR EL  TORNEO- CREAER EL EQUIPO- ASIGNAR EL TORNEO AL EQUIPO

    }

    */

}
