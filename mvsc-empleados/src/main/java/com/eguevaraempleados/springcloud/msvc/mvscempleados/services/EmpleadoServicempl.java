package com.eguevaraempleados.springcloud.msvc.mvscempleados.services;

import com.eguevaraempleados.springcloud.msvc.mvscempleados.entity.Empleado;
import com.eguevaraempleados.springcloud.msvc.mvscempleados.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicempl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository repository;
    @Override
    @Transactional
    public List<Empleado> lista() {
        return (List<Empleado>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Empleado> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return repository.save(empleado);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Empleado> porEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Empleado> porDni(String dni) {
        return repository.findByDni(dni);
    }
}
