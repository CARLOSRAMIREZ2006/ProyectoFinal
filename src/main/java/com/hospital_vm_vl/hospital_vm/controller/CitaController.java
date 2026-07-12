package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.CitaDTO;
import com.hospital_vm_vl.hospital_vm.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    @Autowired
    private CitaService service;

    @GetMapping
    public ResponseEntity<List<CitaDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<CitaDTO> create(@Valid @RequestBody CitaDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}