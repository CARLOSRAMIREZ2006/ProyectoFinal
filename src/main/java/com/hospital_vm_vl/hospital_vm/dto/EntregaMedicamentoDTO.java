package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "DTO para la gestión de entregas de medicamentos")
public class EntregaMedicamentoDTO {

    @Schema(description = "ID del registro de entrega", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El ventaId es obligatorio")
    @Schema(description = "ID de la venta asociada", example = "1")
    private Long ventaId;

    @NotNull(message = "La dirección es obligatoria")
    @Size(min = 5, message = "La dirección debe ser detallada")
    @Schema(description = "Dirección de despacho", example = "Av. Principal 123, Santiago")
    private String direccion;

    @Schema(description = "Estado actual de la entrega", example = "EN_PREPARACION")
    private String estado;
}