package com.eguevarahorarios.springcloud.msvc.msvchorarios.services;

import com.eguevarahorarios.springcloud.msvc.msvchorarios.entities.Horarios;
import com.eguevarahorarios.springcloud.msvc.msvchorarios.repositories.HorariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class HorariosServicempl implements HorariosService{
    private HorariosRepository repository;
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
        repository.deleteById(id);
    }
}
