package com.dev.projects.pruebakosmos.repositories;

import com.dev.projects.pruebakosmos.entities.Appointment;
import com.dev.projects.pruebakosmos.entities.Doctor;
import com.dev.projects.pruebakosmos.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByConsultorioAndFechaHora(Office consultorio, LocalDateTime fechaHora);

    List<Appointment> findByDoctorAndFechaHora(Doctor doctor, LocalDateTime fechaHora);

    List<Appointment> findByNombrePacienteAndFechaHoraBetween(String nombrePaciente, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByDoctorAndFechaHoraBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByDoctorAndFechaHoraBetweenAndEstado(
            Doctor doctor, LocalDateTime desde, LocalDateTime hasta, Appointment.EstadoCita estado
    );

}
