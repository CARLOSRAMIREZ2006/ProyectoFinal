package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class LiquidacionAtencionDTO {
    private Long id;

    @NotNull(message = "El ventaId es obligatorio")
    private Long ventaId;

    private LocalDateTime fechaEmision;

    @Positive(message = "El monto debe ser mayor a cero")
    private Double montoTotal;
}