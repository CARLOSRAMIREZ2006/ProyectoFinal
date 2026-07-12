package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.EntregaMedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.service.EntregaMedicamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrega-medicamento")
public class EntregaMedicamentoController {
    @Autowired
    private EntregaMedicamentoService service;

    @GetMapping
    public ResponseEntity<List<EntregaMedicamentoDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<EntregaMedicamentoDTO> create(@Valid @RequestBody EntregaMedicamentoDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}