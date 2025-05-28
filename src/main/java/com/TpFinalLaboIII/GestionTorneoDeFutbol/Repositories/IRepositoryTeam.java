package com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryTeam extends JpaRepository<Equipo,Long> {

}
