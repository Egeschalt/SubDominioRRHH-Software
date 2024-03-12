package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.services;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.Empleadoss;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.entities.Planilla;

import java.util.List;
import java.util.Optional;

public interface PlanillaService {
    List<Planilla> listar();
    Optional<Planilla> porId(Long id);
    Planilla guardar(Planilla planilla);
    void eliminar(Long id);
    Optional<Empleadoss> asignarEmpleados(Empleadoss empleados, Long planillaId);
    Optional<Empleadoss> crearEmpleados(Empleadoss empleados, Long planillaId);
    Optional<Empleadoss> eliminarEmpleados(Empleadoss empleados, Long planillaId);



}
