-- Script para crear la tabla Doctor
CREATE TABLE Doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido_paterno VARCHAR(255) NOT NULL,
    apellido_materno VARCHAR(255),
    especialidad VARCHAR(255) NOT NULL
);

-- Script para crear la tabla Consultorio
CREATE TABLE Consultorio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_consultorio INT NOT NULL,
    piso INT NOT NULL
);

-- Script para crear la tabla Cita
CREATE TABLE Cita (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    consultorio_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    horario_consulta TIMESTAMP NOT NULL,
    nombre_paciente VARCHAR(255) NOT NULL,
    FOREIGN KEY (consultorio_id) REFERENCES Consultorio(id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(id)
);

