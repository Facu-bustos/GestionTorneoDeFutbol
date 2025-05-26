package com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRepositoryTournaumet extends JpaRepository<Torneo, Long> {


}
