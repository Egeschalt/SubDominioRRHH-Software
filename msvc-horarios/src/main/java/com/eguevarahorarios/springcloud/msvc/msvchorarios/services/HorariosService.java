package com.eguevarahorarios.springcloud.msvc.msvchorarios.services;

import com.eguevarahorarios.springcloud.msvc.msvchorarios.entities.Horarios;

import java.util.List;
import java.util.Optional;

public interface HorariosService {
    List<Horarios> lista();
    Optional<Horarios> porId(Long id);
    Horarios guardar(Horarios horarios);
    void eliminar(Long id);
}
