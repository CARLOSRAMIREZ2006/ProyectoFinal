-- 1. Crear la tabla de Pacientes (Existente)
CREATE TABLE Pacientes (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           rut VARCHAR(12) NOT NULL
);

-- 2. Crear la tabla de Citas (Existente)
CREATE TABLE Cita (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      run VARCHAR(13) NOT NULL,
                      fecha DATE NOT NULL,
                      correo VARCHAR(100) NOT NULL,
                      paciente_id BIGINT NOT NULL
);

-- 3. Crear la tabla de Pago (Basada en tu clase)
CREATE TABLE Pago (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      venta_id BIGINT NOT NULL,
                      monto DOUBLE NOT NULL,
                      metodo_pago VARCHAR(50) NOT NULL
);

-- 4. Crear la tabla de StockFarmacia (Basada en tu clase)
CREATE TABLE StockFarmacia (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               producto_id BIGINT NOT NULL,
                               cantidad INT NOT NULL
);

-- 5. Crear la tabla de Convenios de Salud
CREATE TABLE ConvenioSalud (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               nombre_aseguradora VARCHAR(100) NOT NULL,
                               porcentaje_cobertura DOUBLE NOT NULL
);

-- 6. Crear la tabla de Medicamentos
CREATE TABLE Medicamento (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nombre VARCHAR(100) NOT NULL,
                             descripcion VARCHAR(255),
                             precio DOUBLE NOT NULL
);

-- 7. Crear la tabla de Entrega de Medicamentos
CREATE TABLE EntregaMedicamento (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    paciente_id BIGINT NOT NULL,
                                    medicamento_id BIGINT NOT NULL,
                                    fecha_entrega DATE NOT NULL
);

-- 8. Crear la tabla de Detalle de Atención
CREATE TABLE DetalleAtencion (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 paciente_id BIGINT NOT NULL,
                                 descripcion_procedimiento VARCHAR(255) NOT NULL,
                                 costo_total DOUBLE NOT NULL
);

-- 9. Crear la tabla de Reembolso de Atención
CREATE TABLE ReembolsoAtencion (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   detalle_atencion_id BIGINT NOT NULL,
                                   monto_reembolsado DOUBLE NOT NULL,
                                   fecha_reembolso DATE NOT NULL
);

-- 10. Crear la tabla de Liquidación de Atención
CREATE TABLE LiquidacionAtencion (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     paciente_id BIGINT NOT NULL,
                                     monto_final DOUBLE NOT NULL,
                                     fecha_emision DATE NOT NULL
);

-- 11. Datos iniciales (Seed data)
INSERT INTO Pacientes (nombre, rut) VALUES ('Juan Pérez', '12.345.678-9');
INSERT INTO Pacientes (nombre, rut) VALUES ('Maria Garcia', '15.987.654-3');