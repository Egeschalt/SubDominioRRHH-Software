package com.eguevarapostulante.springcloud.msvc.msvcpostulante.services;


import com.eguevarapostulante.springcloud.msvc.msvcpostulante.entities.Postulante;

import java.util.List;
import java.util.Optional;

public interface PostulanteService {
    List<Postulante> lista();
    Optional<Postulante> porId(Long id);
    Postulante guardar(Postulante empleado);
    void eliminar(Long id);
    Optional<Postulante> porEmail(String email);
    Optional<Postulante> porDni(String dni);
}
