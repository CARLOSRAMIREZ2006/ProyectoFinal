package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class DespachoDTO {
    private Long id;

    @NotNull(message = "El ventaId es obligatorio")
    private Long ventaId;

    @NotNull(message = "La dirección es obligatoria")
    private String direccion;

    private String estado;
}