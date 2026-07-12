package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.MedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.service.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {
    @Autowired
    private MedicamentoService service;

    @GetMapping
    public ResponseEntity<List<MedicamentoDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MedicamentoDTO> create(@Valid @RequestBody MedicamentoDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> update(@PathVariable Long id, @Valid @RequestBody MedicamentoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}