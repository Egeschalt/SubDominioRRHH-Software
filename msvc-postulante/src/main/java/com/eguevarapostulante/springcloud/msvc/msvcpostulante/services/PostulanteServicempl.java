package com.eguevarapostulante.springcloud.msvc.msvcpostulante.services;
import com.eguevarapostulante.springcloud.msvc.msvcpostulante.entities.Postulante;
import com.eguevarapostulante.springcloud.msvc.msvcpostulante.entities.Postulante;
import com.eguevarapostulante.springcloud.msvc.msvcpostulante.repositories.PostulanteRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class PostulanteServicempl implements PostulanteService {
    private PostulanteRepository repository;
    @Override
    @Transactional
    public List<Postulante> lista() {
        return (List<Postulante>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Postulante> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Postulante guardar(Postulante postulante) {
        return repository.save(postulante);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Postulante> porEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Postulante> porDni(String dni) {
        return repository.findByDni(dni);
    }
}
