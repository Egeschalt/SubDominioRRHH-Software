package com.eguevarahorarios.springcloud.msvc.msvchorarios.services;

import com.eguevarahorarios.springcloud.msvc.msvchorarios.clients.HorariosClientRest;
import com.eguevarahorarios.springcloud.msvc.msvchorarios.models.Empleados;
import com.eguevarahorarios.springcloud.msvc.msvchorarios.models.entities.Horarios;
import com.eguevarahorarios.springcloud.msvc.msvchorarios.models.entities.HorariosEmpleados;
import com.eguevarahorarios.springcloud.msvc.msvchorarios.repositories.HorariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HorariosServicempl implements HorariosService{
    private HorariosRepository repository;
    @Autowired
    private HorariosClientRest client;

    @Override
    @Transactional
    public List<Horarios> lista() {
        return (List<Horarios>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Horarios> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Horarios guardar(Horarios horarios) {
        return repository.save(horarios);
    }

    @Override
    public void eliminar(Long id) {

        client.eliminarHorarioEmpleadoPorId(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Empleados> asignarEmpleados(Empleados empleado, Long horariosId) {
        Optional<Horarios> o=repository.findById(horariosId);
        if (o.isPresent()){
            Empleados empleadosMsvc=client.detalle(empleado.getId());
            Horarios horarios=o.get();
            HorariosEmpleados horariosEmpleados=new HorariosEmpleados();
            horariosEmpleados.setEmpleadosId(empleadosMsvc.getId());

            horarios.addHorariosEmpleados((horariosEmpleados));
            repository.save(horarios);
            return  Optional.of(empleadosMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Empleados> crearEmpleados(Empleados empleado, Long horariosId) {
        Optional<Horarios> o = repository.findById(horariosId);
        if(o.isPresent()){
            Empleados empleadosNewMsvc = client.crear(empleado);
            Horarios horarios = o.get();
            HorariosEmpleados horariosEmpleados = new HorariosEmpleados();
            horariosEmpleados.setEmpleadosId(empleadosNewMsvc.getId());
            horarios.addHorariosEmpleados((horariosEmpleados));
            repository.save(horarios);
            return Optional.of(empleadosNewMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Empleados> eliminarEmpleados(Empleados empleado, Long horariosId) {
        Optional<Horarios> o = repository.findById(horariosId);
        if(o.isPresent()){
            Empleados empleadosMsvc = client.detalle(empleado.getId());
            Horarios horarios = o.get();
            HorariosEmpleados horariosEmpleados = new HorariosEmpleados();
            horariosEmpleados.setEmpleadosId(empleadosMsvc.getId());
            horarios.removeHorariosEmpleados((horariosEmpleados));
            repository.save(horarios);
            return Optional.of(empleadosMsvc);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Horarios> porIdConEmpleados(Long id) {
        Optional<Horarios> o = repository.findById(id);
        if(o.isPresent()){
            Horarios horarios = o.get();
            if(!horarios.getListahorariosEmpleados().isEmpty()){
                List<Long> ids = horarios.getListahorariosEmpleados().stream().map(cu -> cu.getEmpleadosId())
                        .collect(Collectors.toList());
                List<Empleados> empleados = client.obtenerEmpleadosporHorario(ids);
                horarios.setEmpleados(empleados);
            }
            return Optional.of(horarios);
        }
        return Optional.empty();

    }

    @Override
    public void eliminarHorarioEmpleadoPorId(Long id) {
        repository.eliminarHorarioEmpleadoPorId(id);
    }



}
