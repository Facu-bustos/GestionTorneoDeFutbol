package com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.DT;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.UnknownServiceException;
import java.util.Optional;

public interface IRepositoryDt extends JpaRepository<DT,Long> {

    Optional<DT> findByUsuarioEmail(String emailDt);
}
