package com.dev.projects.pruebakosmos.services;

import com.dev.projects.pruebakosmos.dto.requests.AppointmentRequestDTO;
import com.dev.projects.pruebakosmos.dto.requests.UpdateAppointmentDTO;
import com.dev.projects.pruebakosmos.dto.responses.ResponseGeneric;

import java.time.LocalDate;

public interface AppointmentsService {
    ResponseGeneric createAppointment(AppointmentRequestDTO nuevaCita);

    ResponseGeneric cancelarCita(Long idCita);

    ResponseGeneric consultarCitasPorDoctorYFecha(Long idDoctor, LocalDate fecha);

    ResponseGeneric actualizarCita(Long idCita, UpdateAppointmentDTO appointmentRequestDTO);
}
