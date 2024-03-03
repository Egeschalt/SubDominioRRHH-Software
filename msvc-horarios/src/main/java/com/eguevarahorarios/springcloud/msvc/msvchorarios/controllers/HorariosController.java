package com.eguevarahorarios.springcloud.msvc.msvchorarios.controllers;

import com.eguevarahorarios.springcloud.msvc.msvchorarios.entities.Horarios;
import com.eguevarahorarios.springcloud.msvc.msvchorarios.services.HorariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/Horarios")
@RestController
public class HorariosController {
    @Autowired
    private HorariosService service;
    @GetMapping
    public List<Horarios> listar(){
        return service.lista();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Horarios> horariosOptional=service.porId(id);
        if (horariosOptional.isPresent()){
            return ResponseEntity.ok(horariosOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Horarios horarios) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(horarios));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Horarios horarios, @PathVariable Long id) {
        Optional<Horarios> op = service.porId(id);
        if (op.isPresent()) {
            Horarios horariosDB = op.get();
            horariosDB.setFecha(horarios.getFecha());
            horariosDB.setHoras(horarios.getHoras());
            horariosDB.setTurno(horarios.getTurno());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(horarios));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Horarios> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
