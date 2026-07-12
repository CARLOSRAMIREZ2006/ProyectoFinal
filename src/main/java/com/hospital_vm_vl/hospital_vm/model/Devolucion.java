package com.hospital_vm_vl.hospital_vm.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "devoluciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Devolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ventaId; // Relación con el microservicio Venta
    private String motivo;
    private LocalDateTime fechaDevolucion;
}