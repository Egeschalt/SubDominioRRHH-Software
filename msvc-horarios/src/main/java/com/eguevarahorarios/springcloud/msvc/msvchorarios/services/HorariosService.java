package com.eguevarahorarios.springcloud.msvc.msvchorarios.services;

import com.eguevarahorarios.springcloud.msvc.msvchorarios.models.Empleados;
import com.eguevarahorarios.springcloud.msvc.msvchorarios.models.entities.Horarios;

import java.util.List;
import java.util.Optional;

public interface HorariosService {
    List<Horarios> lista();
    Optional<Horarios> porId(Long id);
    Horarios guardar(Horarios horarios);
    void eliminar(Long id);
    Optional<Empleados> asignarEmpleados(Empleados empleado, Long horariosId);
    Optional<Empleados> crearEmpleados(Empleados empleado, Long horariosId);
    Optional<Empleados> eliminarEmpleados(Empleados empleado, Long horariosId);
    Optional<Horarios> porIdConEmpleados(Long id);
    void eliminarHorarioEmpleadoPorId(Long id);

}
