package com.dev.projects.pruebakosmos.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

public class AppointmentRequestDTO {
    @NotNull
    private Long idDoctor;
    @NotNull
    private Long idOffice;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaHora;
    @NotNull
    private String nombrePaciente;

    public AppointmentRequestDTO() {
    }

    public AppointmentRequestDTO(Long idDoctor, Long idOffice, LocalDateTime fechaHora, String nombrePaciente) {
        this.idDoctor = idDoctor;
        this.idOffice = idOffice;
        this.fechaHora = fechaHora;
        this.nombrePaciente = nombrePaciente;
    }

    public @NotNull Long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(@NotNull Long idDoctor) {
        this.idDoctor = idDoctor;
    }

    public @NotNull Long getIdOffice() {
        return idOffice;
    }

    public void setIdOffice(@NotNull Long idOffice) {
        this.idOffice = idOffice;
    }

    public @NotNull String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(@NotNull String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public @NotNull LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(@NotNull LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "AppointmentRequestDTO{" +
                "idDoctor=" + idDoctor +
                ", idOffice=" + idOffice +
                ", fechaHora=" + fechaHora +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                '}';
    }
}
