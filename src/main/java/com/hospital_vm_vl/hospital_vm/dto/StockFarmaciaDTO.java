package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Data
public class StockFarmaciaDTO {
    private Long id;

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @PositiveOrZero(message = "La cantidad no puede ser negativa")
    private Integer cantidad;
}