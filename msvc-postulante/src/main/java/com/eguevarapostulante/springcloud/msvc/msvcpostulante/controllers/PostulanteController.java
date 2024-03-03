package com.eguevarapostulante.springcloud.msvc.msvcpostulante.controllers;

import com.eguevarapostulante.springcloud.msvc.msvcpostulante.entities.Postulante;
import com.eguevarapostulante.springcloud.msvc.msvcpostulante.services.PostulanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("api/postulante")
@RestController
public class PostulanteController {
    @Autowired
    private PostulanteService service;
    @GetMapping
    public List<Postulante> listar(){
        return service.lista();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Postulante> postulanteOptional=service.porId(id);
        if (postulanteOptional.isPresent()){
            return ResponseEntity.ok(postulanteOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/postulante")
    public ResponseEntity<?> crear(@Valid @RequestBody Postulante postulante, BindingResult result) {
        if (service.porDni(postulante.getDni()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Ups!!", "Este postulante ya existe"));
        }

        if (service.porEmail(postulante.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error!!", "Este correo ya está siendo usado"));
        }

        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(postulante));
    }

    @PutMapping("/postulante/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Postulante postulante, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }

        Optional<Postulante> op = service.porId(id);
        if (op.isPresent()) {
            Postulante postulanteDB = op.get();

            if (!postulante.getDni().equalsIgnoreCase(postulanteDB.getDni()) && service.porDni(postulante.getDni()).isPresent()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("Upss!!! ", "Ya existe el DNI en otro postulante"));
            }

            if (!postulante.getEmail().equalsIgnoreCase(postulanteDB.getEmail()) && service.porEmail(postulante.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("Error!! ", "Ya existe el email en otro postulante"));
            }

            postulanteDB.setName(postulante.getName());
            postulanteDB.setSuranme(postulante.getSuranme());
            postulanteDB.setEmail(postulante.getEmail());
            postulanteDB.setPhone(postulante.getPhone());
            postulanteDB.setDni(postulante.getDni());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(postulanteDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Postulante> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    private ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Error!!! " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
