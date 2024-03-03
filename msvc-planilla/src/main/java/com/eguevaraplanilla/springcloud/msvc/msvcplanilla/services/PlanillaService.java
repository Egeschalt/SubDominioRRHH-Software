package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.services;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.entities.Planilla;

import java.util.List;
import java.util.Optional;

public interface PlanillaService {
    List<Planilla> listar();
    Optional<Planilla> porId(Long id);
    Planilla guardar(Planilla planilla);
    void eliminar(Long id);
}
