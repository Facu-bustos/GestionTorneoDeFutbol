package com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.DT;
import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRepositoryTeam extends JpaRepository<Equipo,Long> {
    Optional<Equipo> findByDt(DT dt);
    Optional<Equipo>findByIdEquipo(Long IdEquipo);
}
