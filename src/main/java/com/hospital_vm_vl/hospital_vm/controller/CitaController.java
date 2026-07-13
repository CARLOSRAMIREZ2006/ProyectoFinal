package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.CitaDTO;
import com.hospital_vm_vl.hospital_vm.service.CitaService;
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
@RequestMapping("/api/citas")
@Tag(name = "Citas", description = "Gestión de citas médicas y atención de pacientes")
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    @Operation(summary = "Listar todas las citas", description = "Obtiene una lista de todas las citas registradas")
    public ResponseEntity<List<CitaDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cita por ID", description = "Busca una cita específica por su identificador")
    public ResponseEntity<CitaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva cita", description = "Crea una cita nueva en el sistema")
    @ApiResponse(responseCode = "201", description = "Cita creada exitosamente")
    public ResponseEntity<CitaDTO> create(@Valid @RequestBody CitaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cita", description = "Modifica los datos de una cita existente")
    public ResponseEntity<CitaDTO> update(@PathVariable Long id, @Valid @RequestBody CitaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cita", description = "Elimina una cita por su ID único")
    @ApiResponse(responseCode = "204", description = "Cita eliminada correctamente")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}