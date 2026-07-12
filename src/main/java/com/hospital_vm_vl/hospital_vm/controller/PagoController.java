package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.PagoDTO;
import com.hospital_vm_vl.hospital_vm.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@Tag(name = "Pagos", description = "Gestión de transacciones y pagos de atenciones")
public class PagoController {
    @Autowired
    private PagoService service;

    @GetMapping
    @Operation(summary = "Listar pagos", description = "Obtiene todos los pagos registrados")
    public ResponseEntity<List<PagoDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Registrar pago", description = "Crea un nuevo registro de pago en el sistema")
    @ApiResponse(responseCode = "201", description = "Pago registrado exitosamente")
    public ResponseEntity<PagoDTO> create(@Valid @RequestBody PagoDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pago", description = "Elimina un registro de pago por su ID")
    @ApiResponse(responseCode = "204", description = "Pago eliminado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}