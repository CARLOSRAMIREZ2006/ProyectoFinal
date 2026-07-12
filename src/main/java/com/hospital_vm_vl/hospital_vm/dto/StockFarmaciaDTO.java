package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "DTO para la gestión de inventario farmacéutico")
public class StockFarmaciaDTO {

    @Schema(description = "ID del registro", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El ID del producto es obligatorio")
    @Schema(description = "Identificador del medicamento", example = "101")
    private Long productoId;

    @PositiveOrZero(message = "La cantidad no puede ser negativa")
    @Schema(description = "Cantidad disponible en bodega", example = "50")
    private Integer cantidad;
}