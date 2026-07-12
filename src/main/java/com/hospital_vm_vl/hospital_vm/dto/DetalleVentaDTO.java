package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class DetalleVentaDTO {
    private Long id;

    @NotNull(message = "El ventaId es obligatorio")
    private Long ventaId;

    @NotNull(message = "El productoId es obligatorio")
    private Long productoId;

    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    private Double precioUnitario;
}