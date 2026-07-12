package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class ProductoDTO {
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @Positive(message = "El precio debe ser mayor a cero")
    private Double precio;

    private Integer stock;
}