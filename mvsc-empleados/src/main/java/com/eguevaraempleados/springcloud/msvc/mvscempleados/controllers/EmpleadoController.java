package com.eguevaraempleados.springcloud.msvc.mvscempleados.controllers;

import com.eguevaraempleados.springcloud.msvc.mvscempleados.entity.Empleado;
import com.eguevaraempleados.springcloud.msvc.mvscempleados.services.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("api/empleado")
@RestController
public class EmpleadoController {
    @Autowired
    private EmpleadoService service;
    @GetMapping
    public List<Empleado> listar(){
        return service.lista();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Empleado> empleadoOptional=service.porId(id);
        if (empleadoOptional.isPresent()){
            return ResponseEntity.ok(empleadoOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Empleado empleado, BindingResult result) {
        if (service.porDni(empleado.getDni()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error!!", "Ese empleado ya existe"));
        }

        if (service.porEmail(empleado.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error!!", "Este correo ya está siendo usado"));
        }

        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(empleado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Empleado empleado, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }

        Optional<Empleado> op = service.porId(id);
        if (op.isPresent()) {
            Empleado empleadoDB = op.get();

            if (!empleado.getDni().equalsIgnoreCase(empleadoDB.getDni()) && service.porDni(empleado.getDni()).isPresent()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("Error!!! ", "Ya existe el DNI en otro empleado"));
            }

            if (!empleado.getEmail().equalsIgnoreCase(empleadoDB.getEmail()) && service.porEmail(empleado.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("Error!! ", "Ya existe el email en otro empleado"));
            }

            empleadoDB.setName(empleado.getName());
            empleadoDB.setSuranme(empleado.getSuranme());
            empleadoDB.setEmail(empleado.getEmail());
            empleadoDB.setPhone(empleado.getPhone());
            empleadoDB.setDni(empleado.getDni());
            empleadoDB.setSueldo(empleado.getSueldo());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(empleadoDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Empleado> op = service.porId(id);
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
