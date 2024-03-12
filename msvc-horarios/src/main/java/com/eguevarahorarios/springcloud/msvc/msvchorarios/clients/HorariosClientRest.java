package com.eguevarahorarios.springcloud.msvc.msvchorarios.clients;

import com.eguevarahorarios.springcloud.msvc.msvchorarios.models.Empleados;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="mvsc-empleado", url="localhost:8001/api/empleado")
public interface HorariosClientRest {
    @GetMapping("/{id}")
    Empleados detalle(@PathVariable Long id);
    @PostMapping()
    Empleados crear(@RequestBody Empleados empleados);
    @GetMapping("/empleado-por-horario")
    List<Empleados> obtenerEmpleadosporHorario(@RequestParam Iterable<Long> ids);
    @DeleteMapping("/eliminar-horEmple/{id}")
    void eliminarHorarioEmpleadoPorId(@PathVariable Long id);
}
