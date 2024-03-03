package com.eguevaraempleados.springcloud.msvc.mvscempleados.services;

import com.eguevaraempleados.springcloud.msvc.mvscempleados.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> lista();
    Optional<Empleado> porId(Long id);
    Empleado guardar(Empleado empleado);
    void eliminar(Long id);
    Optional<Empleado> porEmail(String email);
    Optional<Empleado> porDni(String dni);
}
