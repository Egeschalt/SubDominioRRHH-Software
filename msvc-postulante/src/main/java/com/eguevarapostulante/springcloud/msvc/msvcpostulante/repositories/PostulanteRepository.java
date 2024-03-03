package com.eguevarapostulante.springcloud.msvc.msvcpostulante.repositories;

import com.eguevarapostulante.springcloud.msvc.msvcpostulante.entities.Postulante;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostulanteRepository extends CrudRepository<Postulante,Long> {
    Optional<Postulante> findByDni(String dni);
    Optional<Postulante> findByEmail(String email);
}
