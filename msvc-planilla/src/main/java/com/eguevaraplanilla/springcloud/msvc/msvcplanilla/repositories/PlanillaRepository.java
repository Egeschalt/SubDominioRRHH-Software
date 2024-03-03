package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.repositories;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.entities.Planilla;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlanillaRepository extends CrudRepository<Planilla,Long> {

}
