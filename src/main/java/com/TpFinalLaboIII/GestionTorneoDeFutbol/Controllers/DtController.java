package com.TpFinalLaboIII.GestionTorneoDeFutbol.Controllers;


import com.TpFinalLaboIII.GestionTorneoDeFutbol.Services.ServicesDt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dt")
public class DtController {
    @Autowired
    private final ServicesDt servicesDt;

    public DtController(ServicesDt servicesDt) {
        this.servicesDt = servicesDt;
    }






}
