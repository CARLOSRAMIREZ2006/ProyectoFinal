package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Data
@Schema(description = "DTO para la gestión de liquidaciones financieras")
public class LiquidacionAtencionDTO {

    @Schema(description = "ID único de la liquidación", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El ventaId es obligatorio")
    @Schema(description = "ID de la venta o atención asociada", example = "1")
    private Long ventaId;

    @Schema(description = "Fecha y hora de emisión", example = "2026-07-12T17:00:00")
    private LocalDateTime fechaEmision;

    @Positive(message = "El monto debe ser mayor a cero")
    @Schema(description = "Monto total de la liquidación", example = "45000.0")
    private Double montoTotal;
}