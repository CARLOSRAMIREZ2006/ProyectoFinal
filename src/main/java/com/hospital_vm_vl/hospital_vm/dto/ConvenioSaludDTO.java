package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "DTO para la gestión de convenios de salud")
public class ConvenioSaludDTO {

    @Schema(description = "ID único del convenio", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    @Schema(description = "Nombre del convenio", example = "Fonasa Plus")
    private String nombre;

    @Positive(message = "El descuento debe ser mayor a cero")
    @Schema(description = "Porcentaje de descuento aplicado", example = "15.0")
    private Double descuento;

    @NotNull(message = "El productoId es obligatorio")
    @Schema(description = "ID del producto vinculado al convenio", example = "1")
    private Long productoId;
}