package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotFoundException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors.NotPostException;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesEstadisticaGoleador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estadistica")
public class EstadisticaGoleadorController {
    @Autowired
    private final ServicesEstadisticaGoleador servicesEstadisticaGoleador;


    public EstadisticaGoleadorController(ServicesEstadisticaGoleador servicesEstadisticaGoleador) {
        this.servicesEstadisticaGoleador = servicesEstadisticaGoleador;
    }

    @PostMapping("/generar/estadisticasGoleador/{idTorneo}")
    public ResponseEntity<String>generateStatistics(@PathVariable long idTorneo)throws NotFoundException, NotPostException
    {
        return servicesEstadisticaGoleador.generateStatistics(idTorneo);
    }


}
