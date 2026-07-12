package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class PagoDTO {
    private Long id;

    @NotNull(message = "El ventaId es obligatorio")
    private Long ventaId;

    @Positive(message = "El monto debe ser mayor a cero")
    private Double monto;

    @NotNull(message = "El método de pago es obligatorio")
    private String metodoPago;
}