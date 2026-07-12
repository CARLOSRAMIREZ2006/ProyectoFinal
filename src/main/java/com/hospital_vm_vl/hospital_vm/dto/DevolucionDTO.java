package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class DevolucionDTO {
    private Long id;

    @NotNull(message = "El ventaId es obligatorio")
    private Long ventaId;

    @NotNull(message = "El motivo es obligatorio")
    private String motivo;

    private LocalDateTime fechaDevolucion;
}