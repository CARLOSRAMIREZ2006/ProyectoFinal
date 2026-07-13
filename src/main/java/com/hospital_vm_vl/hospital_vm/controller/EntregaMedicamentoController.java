package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.EntregaMedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.service.EntregaMedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrega-medicamento")
@Tag(name = "Entrega de Medicamentos", description = "Gestión de los envíos y estados de entrega")
public class EntregaMedicamentoController {

    @Autowired
    private EntregaMedicamentoService service;

    @GetMapping
    @Operation(summary = "Listar entregas", description = "Obtiene todas las entregas registradas")
    public ResponseEntity<List<EntregaMedicamentoDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener entrega por ID")
    public ResponseEntity<EntregaMedicamentoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar entrega")
    @ApiResponse(responseCode = "201", description = "Entrega creada exitosamente")
    public ResponseEntity<EntregaMedicamentoDTO> create(@Valid @RequestBody EntregaMedicamentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar entrega")
    public ResponseEntity<EntregaMedicamentoDTO> update(@PathVariable Long id, @Valid @RequestBody EntregaMedicamentoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar entrega")
    @ApiResponse(responseCode = "204", description = "Entrega eliminada")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}