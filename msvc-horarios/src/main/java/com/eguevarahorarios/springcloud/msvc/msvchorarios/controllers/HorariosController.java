package com.eguevarahorarios.springcloud.msvc.msvchorarios.controllers;

import com.eguevarahorarios.springcloud.msvc.msvchorarios.models.Empleados;
import com.eguevarahorarios.springcloud.msvc.msvchorarios.models.entities.Horarios;
import com.eguevarahorarios.springcloud.msvc.msvchorarios.services.HorariosService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    @PutMapping("/asignar-empleado/{horariosId}")
    public ResponseEntity<?> asignarEmpleados(@RequestBody Empleados empleados, @PathVariable Long
            horariosId){
        Optional<Empleados> o;
        try {
            o=service.asignarEmpleados(empleados, horariosId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No existe el empleado por " + "el id o error en la comunicación: "+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/crear-empleado/{horariosId}")
    public ResponseEntity<?> crearEmpleados(@RequestBody Empleados empleados, @PathVariable Long horariosId){
        Optional<Empleados> o;
        try {
            o=service.crearEmpleados(empleados, horariosId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No se pudo crear el empleado " + "o error en la comunicación: "+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/eliminar-empleado/{horariosId}")
    public ResponseEntity<?> eliminarEmpleados(@RequestBody Empleados empleados, @PathVariable Long horariosId){
        Optional<Empleados> o;
        try {
            o=service.eliminarEmpleados(empleados, horariosId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No existe el empleado por " + "el id o error en la comunicación: "+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();

    }
    @GetMapping("/horEmple/{id}")
    public ResponseEntity<?> detallehorEmple(@PathVariable Long id){
        Optional<Horarios> op = service.porIdConEmpleados(id);
        if(op.isPresent()){
            return ResponseEntity.ok(op.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/eliminar-horEmple/{id}")
    public ResponseEntity<?> eliminarHorarioEmpleadoPorId(@PathVariable Long id){
        service.eliminarHorarioEmpleadoPorId(id);
        return ResponseEntity.noContent().build();
    }

}
