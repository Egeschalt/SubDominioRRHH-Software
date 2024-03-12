package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.clients;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.Empleados;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="mvsc-empleado", url="localhost:8001/api/empleado")
public interface PlanillaClientRest {
    @GetMapping("/{id}")
    Empleados detalle(@PathVariable Long id);
    @PostMapping()
    Empleados crear(@RequestBody Empleados empleados);
    @GetMapping("/empleado-por-Planilla")
    List<Empleados> obtenerEmpleadosporPlanilla(@RequestParam Iterable<Long> ids);
    @DeleteMapping("/eliminar-plaEmple/{id}")
    void eliminarPlanillaEmpleadoPorId(@PathVariable Long id);
}
