package com.eguevarahorarios.springcloud.msvc.msvchorarios.repositories;

import com.eguevarahorarios.springcloud.msvc.msvchorarios.models.entities.Horarios;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface HorariosRepository extends CrudRepository<Horarios,Long> {
    @Modifying
    @Query("delete from HorariosEmpleados cu where cu.empleadosId=?1")
    void eliminarHorarioEmpleadoPorId(Long id);

}
