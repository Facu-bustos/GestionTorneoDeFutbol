package com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryPlayer extends JpaRepository<Jugador, Long> {
}
