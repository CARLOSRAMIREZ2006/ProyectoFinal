package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.MedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.service.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@Tag(name = "Medicamentos", description = "Gestión del catálogo de medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @GetMapping
    @Operation(summary = "Listar medicamentos", description = "Obtiene el catálogo completo de medicamentos")
    public ResponseEntity<List<MedicamentoDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Obtiene un medicamento específico por su ID")
    public ResponseEntity<MedicamentoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear medicamento", description = "Registra un nuevo medicamento en el catálogo")
    @ApiResponse(responseCode = "201", description = "Medicamento creado exitosamente")
    public ResponseEntity<MedicamentoDTO> create(@Valid @RequestBody MedicamentoDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar medicamento", description = "Modifica los datos de un medicamento existente")
    public ResponseEntity<MedicamentoDTO> update(@PathVariable Long id, @Valid @RequestBody MedicamentoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar medicamento", description = "Elimina un medicamento del catálogo")
    @ApiResponse(responseCode = "204", description = "Medicamento eliminado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}