package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.LiquidacionAtencionDTO;
import com.hospital_vm_vl.hospital_vm.service.LiquidacionAtencionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/liquidacion-atencion")
@Tag(name = "Liquidación de Atención", description = "Gestión de cobros y liquidaciones financieras")
public class LiquidacionAtencionController {

    @Autowired
    private LiquidacionAtencionService service;

    @GetMapping
    @Operation(summary = "Listar liquidaciones", description = "Obtiene todas las liquidaciones emitidas")
    public ResponseEntity<List<LiquidacionAtencionDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Registrar liquidación", description = "Crea una nueva liquidación de atención médica")
    @ApiResponse(responseCode = "201", description = "Liquidación creada exitosamente")
    public ResponseEntity<LiquidacionAtencionDTO> create(@Valid @RequestBody LiquidacionAtencionDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar liquidación", description = "Elimina una liquidación por su ID")
    @ApiResponse(responseCode = "204", description = "Liquidación eliminada")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}