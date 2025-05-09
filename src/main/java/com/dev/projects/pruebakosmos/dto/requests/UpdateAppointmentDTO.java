package com.dev.projects.pruebakosmos.dto.requests;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public class UpdateAppointmentDTO {
    @NotNull(message = "El ID del doctor es obligatorio")
    private Long idDoctor;

    @NotNull(message = "El ID del consultorio es obligatorio")
    private Long idOffice;

    @NotNull(message = "La fecha y hora de la cita es obligatoria")
    private LocalDateTime fechaHora;

    @NotNull(message = "El nombre del paciente es obligatorio")
    private String nombrePaciente;

    public UpdateAppointmentDTO() {
    }

    public UpdateAppointmentDTO(Long idDoctor, Long idOffice, LocalDateTime fechaHora, String nombrePaciente) {
        this.idDoctor = idDoctor;
        this.idOffice = idOffice;
        this.fechaHora = fechaHora;
        this.nombrePaciente = nombrePaciente;
    }

    public @NotNull(message = "El ID del doctor es obligatorio") Long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(@NotNull(message = "El ID del doctor es obligatorio") Long idDoctor) {
        this.idDoctor = idDoctor;
    }

    public @NotNull(message = "El ID del consultorio es obligatorio") Long getIdOffice() {
        return idOffice;
    }

    public void setIdOffice(@NotNull(message = "El ID del consultorio es obligatorio") Long idOffice) {
        this.idOffice = idOffice;
    }

    public @NotNull(message = "La fecha y hora de la cita es obligatoria") LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(@NotNull(message = "La fecha y hora de la cita es obligatoria") LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public @NotNull(message = "El nombre del paciente es obligatorio") String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(@NotNull(message = "El nombre del paciente es obligatorio") String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
}
