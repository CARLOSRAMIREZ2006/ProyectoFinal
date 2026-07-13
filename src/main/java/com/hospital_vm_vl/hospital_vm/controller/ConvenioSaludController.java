package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.ConvenioSaludDTO;
import com.hospital_vm_vl.hospital_vm.service.ConvenioSaludService;
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
@RequestMapping("/api/convenio-salud")
@Tag(name = "Convenios de Salud", description = "Gestión de convenios y descuentos de salud")
public class ConvenioSaludController {

    @Autowired
    private ConvenioSaludService service;

    @GetMapping
    @Operation(summary = "Listar convenios", description = "Obtiene todos los convenios registrados")
    public ResponseEntity<List<ConvenioSaludDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener convenio por ID", description = "Busca un convenio específico")
    public ResponseEntity<ConvenioSaludDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear convenio", description = "Registra un nuevo convenio de salud")
    @ApiResponse(responseCode = "201", description = "Convenio creado con éxito")
    public ResponseEntity<ConvenioSaludDTO> create(@Valid @RequestBody ConvenioSaludDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar convenio", description = "Modifica un convenio existente")
    public ResponseEntity<ConvenioSaludDTO> update(@PathVariable Long id, @Valid @RequestBody ConvenioSaludDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar convenio", description = "Elimina un convenio por su ID")
    @ApiResponse(responseCode = "204", description = "Convenio eliminado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}