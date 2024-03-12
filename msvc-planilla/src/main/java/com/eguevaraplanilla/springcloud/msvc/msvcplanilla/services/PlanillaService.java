package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.services;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.Empleados;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.entities.Planilla;

import java.util.List;
import java.util.Optional;

public interface PlanillaService {
    List<Planilla> listar();
    Optional<Planilla> porId(Long id);
    Planilla guardar(Planilla planilla);
    void eliminar(Long id);
    Optional<Empleados> asignarEmpleados(Empleados empleado, Long planillaId);
    Optional<Empleados> crearEmpleados(Empleados empleado, Long planillaId);
    Optional<Empleados> eliminarEmpleados(Empleados empleado, Long planillaId);
    Optional<Planilla> porIdConEmpleados(Long id);
    void eliminarPlanillaEmpleadoPorId(Long id);
}
