package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.repositories;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.entities.Planilla;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlanillaRepository extends CrudRepository<Planilla,Long> {
    @Modifying
    @Query("delete from PlanillaEmpleados cu where cu.empleadosId=?1")
    void eliminarPlanillaEmpleadoPorId(Long id);
}
