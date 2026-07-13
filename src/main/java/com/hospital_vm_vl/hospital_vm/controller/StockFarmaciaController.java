package com.hospital_vm_vl.hospital_vm.controller;

import com.hospital_vm_vl.hospital_vm.dto.StockFarmaciaDTO;
import com.hospital_vm_vl.hospital_vm.service.StockFarmaciaService;
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
@RequestMapping("/api/stock-farmacia")
@Tag(name = "Stock Farmacia", description = "Gestión del inventario de medicamentos")
public class StockFarmaciaController {

    @Autowired
    private StockFarmaciaService service;

    @GetMapping
    @Operation(summary = "Listar stock", description = "Obtiene todo el inventario disponible")
    public ResponseEntity<List<StockFarmaciaDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener stock por ID", description = "Busca un ítem de stock específico")
    public ResponseEntity<StockFarmaciaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo stock", description = "Añade un medicamento al inventario")
    @ApiResponse(responseCode = "201", description = "Stock creado con éxito")
    public ResponseEntity<StockFarmaciaDTO> create(@Valid @RequestBody StockFarmaciaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar stock", description = "Modifica la cantidad de un medicamento existente")
    public ResponseEntity<StockFarmaciaDTO> update(@PathVariable Long id, @Valid @RequestBody StockFarmaciaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar stock", description = "Elimina un registro de stock del sistema")
    @ApiResponse(responseCode = "204", description = "Registro eliminado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}