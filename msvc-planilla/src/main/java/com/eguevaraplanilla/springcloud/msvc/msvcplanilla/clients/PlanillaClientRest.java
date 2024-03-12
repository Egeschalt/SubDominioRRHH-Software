package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.clients;


import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.Empleadoss;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="msvc-usuarios", url="localhost:8001/api/usuario")
public interface PlanillaClientRest {
    @GetMapping("/{id}")
    Empleadoss detalle(@PathVariable Long id);
    @PostMapping()
    Empleadoss crear(@RequestBody Empleadoss empleados);

}
