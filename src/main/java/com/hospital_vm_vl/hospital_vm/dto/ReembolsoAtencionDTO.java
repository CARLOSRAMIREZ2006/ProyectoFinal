package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Data
@Schema(description = "DTO para la gestión de reembolsos")
public class ReembolsoAtencionDTO {

    @Schema(description = "ID único del reembolso", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El ventaId es obligatorio")
    @Schema(description = "ID de la venta asociada", example = "1")
    private Long ventaId;

    @NotNull(message = "El motivo es obligatorio")
    @Size(min = 5, message = "El motivo debe ser detallado")
    @Schema(description = "Razón del reembolso", example = "Error en cobro de consulta")
    private String motivo;

    @Schema(description = "Fecha de la devolución", example = "2026-07-12T18:00:00")
    private LocalDateTime fechaDevolucion;
}