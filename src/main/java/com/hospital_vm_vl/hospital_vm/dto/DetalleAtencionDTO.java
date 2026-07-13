package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "DTO para la gestión de detalles de atención")
public class DetalleAtencionDTO {

    @Schema(description = "ID del detalle", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El ventaId es obligatorio")
    @Schema(description = "ID de la venta o atención asociada", example = "1")
    private Long ventaId;

    @NotNull(message = "El productoId es obligatorio")
    @Schema(description = "ID del producto o medicamento", example = "101")
    private Long productoId;

    @Positive(message = "La cantidad debe ser mayor a cero")
    @Schema(description = "Cantidad del producto", example = "2")
    private Integer cantidad;

    @Positive(message = "El precio unitario debe ser mayor a cero")
    @Schema(description = "Precio unitario del producto", example = "1500.0")
    private Double precioUnitario;
}