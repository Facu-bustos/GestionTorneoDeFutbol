package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.DTOS.ViewUserDTDTO;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesDt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/dt")
public class DtController {
    @Autowired
    private final ServicesDt servicesDt;

    public DtController(ServicesDt servicesDt) {
        this.servicesDt = servicesDt;
    }


    @DeleteMapping("/jugador/{jugadorId}")
    public ResponseEntity<?> eliminarJugador(@PathVariable long jugadorId, Principal principal) {
        try {
            servicesDt.deletePlayerById(jugadorId, principal.getName());
            return ResponseEntity.ok("Jugador eliminado correctamente");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (NotPostException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/listDTS")
    @ResponseBody
    public List<ViewUserDTDTO>listAllDts() throws NotFoundException
    {
        return servicesDt.listAllDts();
    }

}





