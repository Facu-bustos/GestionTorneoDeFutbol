package com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRepositoryFixture extends JpaRepository<Fixture,Long> {
    List<Fixture> findByNombreTorneoIdTorneoOrderByFechaPartidoAsc(Long idTorneo);
}
