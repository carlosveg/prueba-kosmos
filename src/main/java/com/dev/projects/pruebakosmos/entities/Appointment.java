package com.dev.projects.pruebakosmos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Doctor doctor;

    @ManyToOne(optional = false)
    private Office consultorio;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private String nombrePaciente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado = EstadoCita.PENDIENTE;

    public enum EstadoCita {
        PENDIENTE,
        CANCELADA,
        REALIZADA
    }

    public Appointment() {
    }

    public Appointment(Long id, EstadoCita estado, LocalDateTime fechaHora, Office consultorio, Doctor doctor, String nombrePaciente) {
        this.id = id;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.consultorio = consultorio;
        this.doctor = doctor;
        this.nombrePaciente = nombrePaciente;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", consultorio=" + consultorio +
                ", fechaHora=" + fechaHora +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                ", estado=" + estado +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Office getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Office consultorio) {
        this.consultorio = consultorio;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
}
