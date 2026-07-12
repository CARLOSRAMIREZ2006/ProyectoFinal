package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class VentaDTO {
    private Long id;

    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;

    private LocalDateTime fecha;
    private Double total;
}