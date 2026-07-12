package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.FutureOrPresent;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Data
@Schema(description = "DTO para la creación y gestión de citas médicas")
public class CitaDTO {

    @Schema(description = "ID único de la cita", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El clienteId es obligatorio")
    @Schema(description = "ID del cliente asociado a la cita", example = "1")
    private Long clienteId;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser en el pasado")
    @Schema(description = "Fecha y hora programada para la cita", example = "2026-12-31T10:00:00")
    private LocalDateTime fecha;

    @NotNull(message = "El total es obligatorio")
    @Min(value = 0, message = "El total no puede ser negativo")
    @Schema(description = "Costo total de la atención", example = "50000")
    private Double total;
}