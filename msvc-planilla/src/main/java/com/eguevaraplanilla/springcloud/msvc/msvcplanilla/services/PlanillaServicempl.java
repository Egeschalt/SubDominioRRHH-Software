package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.services;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.clients.PlanillaClientRest;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.Empleados;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.entities.Planilla;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.entities.PlanillaEmpleados;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.repositories.PlanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanillaServicempl implements PlanillaService{
    @Autowired
    private PlanillaRepository repository;
    @Autowired
    private PlanillaClientRest client;
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
        repository.deleteById(id);
        client.eliminarPlanillaEmpleadoPorId(id);
    }

    @Override
    @Transactional
    public Optional<Empleados> asignarEmpleados(Empleados empleado, Long planillaId) {
        Optional<Planilla> o=repository.findById(planillaId);
        if (o.isPresent()){
            Empleados empleadosMsvc=client.detalle(empleado.getId());
            Planilla planilla=o.get();
            PlanillaEmpleados planillaEmpleados=new PlanillaEmpleados();
            planillaEmpleados.setEmpleadosId(empleadosMsvc.getId());

            planilla.addPlanillaEmpleados((planillaEmpleados));
            repository.save(planilla);
            return  Optional.of(empleadosMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Empleados> crearEmpleados(Empleados empleado, Long planillaId) {
        Optional<Planilla> o = repository.findById(planillaId);
        if(o.isPresent()){
            Empleados empleadosNewMsvc = client.crear(empleado);
            Planilla planilla = o.get();
            PlanillaEmpleados planillaEmpleados = new PlanillaEmpleados();
            planillaEmpleados.setEmpleadosId(empleadosNewMsvc.getId());
            planilla.addPlanillaEmpleados(( planillaEmpleados));
            repository.save(planilla);
            return Optional.of(empleadosNewMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Empleados> eliminarEmpleados(Empleados empleado, Long planillaId) {
        Optional<Planilla> o = repository.findById(planillaId);
        if(o.isPresent()){
            Empleados empleadosMsvc = client.detalle(empleado.getId());
            Planilla planilla = o.get();
            PlanillaEmpleados planillaEmpleados = new PlanillaEmpleados();
            planillaEmpleados.setEmpleadosId(empleadosMsvc.getId());
            planilla.removePlanillaEmpleados((planillaEmpleados));
            repository.save(planilla);
            return Optional.of(empleadosMsvc);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Planilla> porIdConEmpleados(Long id) {
        Optional<Planilla> o = repository.findById(id);
        if(o.isPresent()){
            Planilla planilla = o.get();
            if(!planilla.getListaplanillaEmpleados().isEmpty()){
                List<Long> ids = planilla.getListaplanillaEmpleados().stream().map(cu -> cu.getEmpleadosId())
                        .collect(Collectors.toList());
                List<Empleados> empleados = client.obtenerEmpleadosporPlanilla(ids);
                planilla.setEmpleados(empleados);
            }
            return Optional.of(planilla);
        }
        return Optional.empty();
    }

    @Override
    public void eliminarPlanillaEmpleadoPorId(Long id) {
        repository.eliminarPlanillaEmpleadoPorId(id);
    }
}
