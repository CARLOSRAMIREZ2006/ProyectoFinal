package com.hospital_vm_vl.hospital_vm.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ventaId; // Relación con el microservicio Venta
    private Double monto;
    private String metodoPago; // Ej: Tarjeta, Efectivo
}