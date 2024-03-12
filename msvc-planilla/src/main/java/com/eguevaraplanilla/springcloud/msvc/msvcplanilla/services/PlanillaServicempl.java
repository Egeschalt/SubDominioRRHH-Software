package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.services;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.clients.PlanillaClientRest;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.Empleadoss;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.entities.Planilla;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.entities.PlanillaEmpleados;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.repositories.PlanillaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class PlanillaServicempl implements PlanillaService{
    @Autowired
    private PlanillaClientRest client;
    @Autowired
    private PlanillaRepository repository;
    @Override
    public List<Planilla> listar() {
        return (List<Planilla>) repository.findAll();
    }

    @Override
    public Optional<Planilla> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Planilla guardar(Planilla planilla) {
        return repository.save(planilla);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);}

    @Override
    @Transactional
    public Optional<Empleadoss> asignarEmpleados(Empleadoss empleados, Long planillaId) {
        Optional<Planilla> o = repository.findById(planillaId);
        if(o.isPresent()){
            Empleadoss empleadosMsvc = client.detalle(empleados.getId());
            Planilla curso = o.get();
            PlanillaEmpleados planillaEmpleados = new PlanillaEmpleados();
            planillaEmpleados.setEmpleadosId(empleadosMsvc.getId());
            curso.addPlanillaEmpleados((planillaEmpleados));
            repository.save(curso);
            return Optional.of(empleadosMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Empleadoss> crearEmpleados(Empleadoss empleados, Long planillaId) {
        Optional<Planilla> o = repository.findById(planillaId);
        if(o.isPresent()){
            Empleadoss empleadosNewMsvc = client.crear(empleados);
            Planilla planilla = o.get();
            PlanillaEmpleados planillaEmpleados = new PlanillaEmpleados();
            planillaEmpleados.setEmpleadosId(empleadosNewMsvc.getId());
            planilla.addPlanillaEmpleados((planillaEmpleados));
            repository.save(planilla);
            return Optional.of(empleadosNewMsvc);
        }
        return Optional.empty();

    }

    @Override
    @Transactional
    public Optional<Empleadoss> eliminarEmpleados(Empleadoss empleados, Long planillaId) {
        Optional<Planilla> o = repository.findById(planillaId);
        if(o.isPresent()){
            Empleadoss empleadosNewMsvc = client.detalle(empleados.getId());
            Planilla planilla = o.get();
            PlanillaEmpleados planillaEmpleados = new PlanillaEmpleados();
            planillaEmpleados.setEmpleadosId(empleadosNewMsvc.getId());
            planilla.removeCursoUsuario((planillaEmpleados));
            repository.save(planilla);
            return Optional.of(empleadosNewMsvc);
        }
        return Optional.empty();

    }


}
