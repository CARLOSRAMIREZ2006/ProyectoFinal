package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.DetalleAtencionDTO;
import com.hospital_vm_vl.hospital_vm.service.DetalleAtencionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detalles-atencion")
@Tag(name = "Detalles de Atención", description = "Gestión de los ítems de cada atención médica")
public class DetalleAtencionController {

    @Autowired
    private DetalleAtencionService service;

    @GetMapping
    @Operation(summary = "Listar detalles", description = "Obtiene todos los registros de detalles de atención")
    public ResponseEntity<List<DetalleAtencionDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Registrar detalle", description = "Crea un nuevo detalle asociado a una atención")
    @ApiResponse(responseCode = "201", description = "Detalle creado exitosamente")
    public ResponseEntity<DetalleAtencionDTO> create(@Valid @RequestBody DetalleAtencionDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar detalle", description = "Elimina un registro de detalle por su ID")
    @ApiResponse(responseCode = "204", description = "Detalle eliminado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}