package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.controllers;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.Empleados;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.entities.Planilla;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.services.PlanillaService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/Planilla")
@RestController
public class PlanillaController {
    @Autowired
    private PlanillaService service;

    @GetMapping
    public List<Planilla> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Planilla> planillaOptional = service.porId(id);
        if (planillaOptional.isPresent()) {
            return ResponseEntity.ok(planillaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Planilla planilla) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(planilla));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Planilla planilla, @PathVariable Long id) {
        Optional<Planilla> op = service.porId(id);
        if (op.isPresent()) {
            Planilla planillaDB = op.get();
            planillaDB.setNumerorecibo(planilla.getNumerorecibo());
            planillaDB.setIngresoimponible(planilla.getIngresoimponible());
            planillaDB.setLiquidacion(planilla.getLiquidacion());
            planillaDB.setFechaliquidacion(planilla.getFechaliquidacion());
            planillaDB.setDescuentos(planilla.getDescuentos());
            return
                    ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(planillaDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Planilla> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/asignar-empleado/{planillaId}")
    public ResponseEntity<?> asignarEmpleados(@RequestBody Empleados empleados, @PathVariable Long planillaId){
        Optional<Empleados> o;
        try {
            o=service.asignarEmpleados(empleados, planillaId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No existe el empleado por " + "el id o error en la comunicación: "+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/crear-empleado/{planillaId}")
    public ResponseEntity<?> crearEmpleados(@RequestBody Empleados empleados, @PathVariable Long  planillaId){
        Optional<Empleados> o;
        try {
            o=service.crearEmpleados(empleados, planillaId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No se pudo crear el empleado " + "o error en la comunicación: "+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/eliminar-empleado/{planillaId}")
    public ResponseEntity<?> eliminarEmpleados(@RequestBody Empleados empleados, @PathVariable Long  planillaId){
        Optional<Empleados> o;
        try {
            o=service.eliminarEmpleados(empleados, planillaId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No existe el empleado por " + "el id o error en la comunicación: "+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();

    }
    @GetMapping("/plaEmple/{id}")
    public ResponseEntity<?> detalleplaEmple(@PathVariable Long id){
        Optional<Planilla> op = service.porIdConEmpleados(id);
        if(op.isPresent()){
            return ResponseEntity.ok(op.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/eliminar-plaEmple/{id}")
    public ResponseEntity<?> eliminarPlanillaEmpleadoPorId(@PathVariable Long id){
        service.eliminarPlanillaEmpleadoPorId(id);
        return ResponseEntity.noContent().build();
    }
}