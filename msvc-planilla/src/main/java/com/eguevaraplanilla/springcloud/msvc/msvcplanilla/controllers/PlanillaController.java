package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.controllers;

import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.entities.Planilla;
import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/planilla")
@RestController
public class PlanillaController {
    @Autowired
    private PlanillaService service;

    @GetMapping
    public List<Planilla> listar() {
        return service.listar();
    }

    @GetMapping("/i{d}")
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
}