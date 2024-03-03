package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.services;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.entities.Planilla;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.repositories.PlanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanillaServicempl implements PlanillaService{
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
        repository.deleteById(id);
    }
}
