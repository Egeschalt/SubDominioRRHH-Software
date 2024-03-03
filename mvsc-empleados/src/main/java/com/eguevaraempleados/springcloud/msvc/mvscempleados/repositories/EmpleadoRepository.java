package com.eguevaraempleados.springcloud.msvc.mvscempleados.repositories;

import com.eguevaraempleados.springcloud.msvc.mvscempleados.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmpleadoRepository extends CrudRepository<Empleado,Long> {
    Optional<Empleado> findByDni(String dni);
    Optional<Empleado> findByEmail(String email);
}
