package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "DTO para la gestión de medicamentos")
public class MedicamentoDTO {

    @Schema(description = "ID único del medicamento", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    @Schema(description = "Nombre comercial del medicamento", example = "Paracetamol")
    private String nombre;

    @Positive(message = "El precio debe ser mayor a cero")
    @Schema(description = "Precio de venta al público", example = "2500.0")
    private Double precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @Schema(description = "Stock actual en bodega", example = "100")
    private Integer stock;
}