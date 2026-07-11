-- 1. Crear la tabla de Pacientes
CREATE TABLE Pacientes (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           rut VARCHAR(12) NOT NULL
);

-- 2. Crear la tabla de Citas (Aislada lógicamente)
CREATE TABLE Cita (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      run VARCHAR(13) NOT NULL,
                      fecha DATE NOT NULL,
                      correo VARCHAR(100) NOT NULL,
                      paciente_id BIGINT NOT NULL -- Solo el ID, sin CONSTRAINT FOREIGN KEY [cite: 117]
);

-- 3. Datos iniciales (Seed data) [cite: 42, 98-102]
INSERT INTO Pacientes (nombre, rut) VALUES ('Juan Pérez', '12.345.678-9');
INSERT INTO Pacientes (nombre, rut) VALUES ('Maria Garcia', '15.987.654-3');