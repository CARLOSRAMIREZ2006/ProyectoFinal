package com.hospital_vm_vl.hospital_vm.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "DTO para la gestión de pagos")
public class PagoDTO {

    @Schema(description = "ID único del pago", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El ventaId es obligatorio")
    @Schema(description = "ID de la venta asociada", example = "1")
    private Long ventaId;

    @Positive(message = "El monto debe ser mayor a cero")
    @Schema(description = "Monto pagado", example = "25000.0")
    private Double monto;

    @NotNull(message = "El método de pago es obligatorio")
    @Size(min = 3, message = "El método de pago debe tener al menos 3 caracteres")
    @Schema(description = "Método utilizado para el pago", example = "TARJETA_CREDITO")
    private String metodoPago;
}