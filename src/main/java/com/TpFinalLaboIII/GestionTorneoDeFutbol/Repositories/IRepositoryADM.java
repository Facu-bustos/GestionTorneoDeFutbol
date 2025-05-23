package com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryADM extends JpaRepository<Administrador, Long> {
}
