package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Data
public class PacienteDTO {
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Debe ser un email válido")
    private String email;

    private String telefono;
}