package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.ReembolsoAtencionDTO;
import com.hospital_vm_vl.hospital_vm.service.ReembolsoAtencionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reembolso-atencion")
@Tag(name = "Reembolsos", description = "Gestión de solicitudes de reembolso por atenciones")
public class ReembolsoAtencionController {

    @Autowired
    private ReembolsoAtencionService service;

    @GetMapping
    @Operation(summary = "Listar reembolsos", description = "Obtiene todos los reembolsos registrados")
    public ResponseEntity<List<ReembolsoAtencionDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Crear reembolso", description = "Registra una nueva solicitud de reembolso")
    @ApiResponse(responseCode = "201", description = "Reembolso creado exitosamente")
    public ResponseEntity<ReembolsoAtencionDTO> create(@Valid @RequestBody ReembolsoAtencionDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reembolso", description = "Elimina una solicitud de reembolso por ID")
    @ApiResponse(responseCode = "204", description = "Reembolso eliminado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}