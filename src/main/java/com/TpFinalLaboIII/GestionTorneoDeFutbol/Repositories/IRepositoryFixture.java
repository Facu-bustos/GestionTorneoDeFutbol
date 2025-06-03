package com.TpFinalLaboIII.GestionTorneoDeFutbol.Repositories;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Models.Entities.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryFixture extends JpaRepository<Fixture,Long> {

}
