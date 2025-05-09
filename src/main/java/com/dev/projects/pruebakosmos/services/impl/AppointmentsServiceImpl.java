package com.dev.projects.pruebakosmos.services.impl;

import com.dev.projects.pruebakosmos.controllers.AppointmentController;
import com.dev.projects.pruebakosmos.dto.requests.AppointmentRequestDTO;
import com.dev.projects.pruebakosmos.dto.requests.UpdateAppointmentDTO;
import com.dev.projects.pruebakosmos.dto.responses.ResponseGeneric;
import com.dev.projects.pruebakosmos.entities.Appointment;
import com.dev.projects.pruebakosmos.entities.Doctor;
import com.dev.projects.pruebakosmos.entities.Office;
import com.dev.projects.pruebakosmos.exceptions.BadRequestException;
import com.dev.projects.pruebakosmos.exceptions.InternalServerErrorException;
import com.dev.projects.pruebakosmos.exceptions.NotFoundException;

import com.dev.projects.pruebakosmos.repositories.AppointmentRepository;
import com.dev.projects.pruebakosmos.repositories.DoctorRepository;
import com.dev.projects.pruebakosmos.repositories.OfficeRepository;
import com.dev.projects.pruebakosmos.services.AppointmentsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    private final Logger log = LoggerFactory.getLogger(AppointmentController.class.getName());
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final OfficeRepository officeRepository;

    @Autowired
    public AppointmentsServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, OfficeRepository officeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.officeRepository = officeRepository;
    }

    @Override
    @Transactional
    public ResponseGeneric createAppointment(AppointmentRequestDTO newAppointment) {
        Doctor doctor = doctorRepository.findById(newAppointment.getIdDoctor()).orElseThrow(() -> new NotFoundException("Doctor no encontrado"));
        Office office = officeRepository.findById(newAppointment.getIdOffice()).orElseThrow(() -> new NotFoundException("Consultorio no encontrado"));
        LocalDateTime fechaHora = newAppointment.getFechaHora();
        ResponseGeneric response = new ResponseGeneric();

        validarCita(doctor, office, fechaHora, newAppointment.getNombrePaciente());

        Appointment appointment = new Appointment();

        try {
            appointment.setNombrePaciente(newAppointment.getNombrePaciente());
            appointment.setFechaHora(fechaHora);
            appointment.setEstado(Appointment.EstadoCita.PENDIENTE);
            appointment.setConsultorio(office);
            appointment.setDoctor(doctor);

            appointment = appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al crear la cita.");
        }

        response.setStatusCode(201);
        response.setMessageCode("Operación realizada con éxito");
        response.setData(appointment);

        return response;
    }

    @Override
    public ResponseGeneric cancelarCita(Long idCita) {
        Appointment appointment = appointmentRepository.findById(idCita)
                .orElseThrow(() -> new NotFoundException("Cita no encontrada"));
        ResponseGeneric response = new ResponseGeneric();

        if (appointment.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("No se puede cancelar una cita pasada");
        }

        appointment.setEstado(Appointment.EstadoCita.CANCELADA);

        try {
            appointment = appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al crear la cita.");
        }

        response.setStatusCode(201);
        response.setMessageCode("Operación realizada con éxito");
        response.setData(appointment);

        return response;
    }

    @Override
    public ResponseGeneric consultarCitasPorDoctorYFecha(Long idDoctor, LocalDate fecha) {
        Doctor doctor = doctorRepository.findById(idDoctor).orElseThrow(() -> new NotFoundException("Doctor no encontrado"));
        ResponseGeneric response = new ResponseGeneric();
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);

        List<Appointment> appointments = appointmentRepository.findByDoctorAndFechaHoraBetweenAndEstado(doctor, inicio, fin, Appointment.EstadoCita.PENDIENTE)
                .stream()
                .toList();

        response.setStatusCode(201);
        response.setMessageCode("Operación realizada con éxito");
        response.setData(appointments);

        return response;
    }

    @Override
    public ResponseGeneric actualizarCita(Long idCita, UpdateAppointmentDTO updateAppointmentDTO) {
        Appointment citaExistente = appointmentRepository.findById(idCita)
                .orElseThrow(() -> new BadRequestException("Cita no encontrada"));

        Doctor doctor = doctorRepository.findById(updateAppointmentDTO.getIdDoctor()).orElseThrow(() -> new NotFoundException("Doctor no encontrado"));
        Office office = officeRepository.findById(updateAppointmentDTO.getIdOffice()).orElseThrow(() -> new NotFoundException("Consultorio no encontrado"));
        LocalDateTime fechaHora = updateAppointmentDTO.getFechaHora();

        validarCita(doctor, office, fechaHora, updateAppointmentDTO.getNombrePaciente());

        try {
            citaExistente.setNombrePaciente(updateAppointmentDTO.getNombrePaciente());
            citaExistente.setFechaHora(fechaHora);
            citaExistente.setConsultorio(office);
            citaExistente.setDoctor(doctor);

            citaExistente = appointmentRepository.save(citaExistente);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al crear la cita.");
        }

        ResponseGeneric response = new ResponseGeneric();

        response.setStatusCode(201);
        response.setMessageCode("Operación realizada con éxito");
        response.setData(citaExistente);

        return response;
    }

    private void validarCita(Doctor doctor, Office office, LocalDateTime fechaHora, String nombrePaciente) {
        LocalDate fecha = fechaHora.toLocalDate();

        // 1. No se puede agendar cita en un mismo consultorio a la misma hora
        log.info("Validando si ya hay una cita en el consultorio {} a la hora {}.", office.getId(), fechaHora);
        if (!appointmentRepository.findByConsultorioAndFechaHora(office, fechaHora).isEmpty()) {
            throw new BadRequestException("Ya hay una cita en este consultorio a esa hora.");
        }

        // 2. No se puede agendar cita para un mismo doctor a la misma hora
        log.info("Validando si ya hay una cita para el doctor {} a la hora {}.", doctor.getId(), fechaHora);
        if (!appointmentRepository.findByDoctorAndFechaHora(doctor, fechaHora).isEmpty()) {
            throw new BadRequestException("El doctor ya tiene una cita a esa hora.");
        }

        // 3. Paciente no puede tener otra cita el mismo día con menos de 2h de diferencia
        log.info("Validando que Paciente no puede tener otra cita el mismo día con menos de 2h de diferencia");
        LocalDateTime desde = fechaHora.minusHours(2);
        LocalDateTime hasta = fechaHora.plusHours(2);
        List<Appointment> citasPaciente = appointmentRepository.findByNombrePacienteAndFechaHoraBetween(
                nombrePaciente, desde, hasta
        );

        for (Appointment cita : citasPaciente) {
            if (cita.getFechaHora().toLocalDate().equals(fecha)) {
                throw new BadRequestException("El paciente ya tiene una cita cercana en el mismo día.");
            }
        }

        // 4. Un doctor no puede tener más de 8 citas en el mismo día
        log.info("Validando que un doctor no puede tener más de 8 citas en el mismo día");
        LocalDateTime inicioDia = LocalDateTime.of(fecha, LocalTime.MIN);
        LocalDateTime finDia = LocalDateTime.of(fecha, LocalTime.MAX);

        List<Appointment> citasDelDoctor = appointmentRepository.findByDoctorAndFechaHoraBetween(
                doctor, inicioDia, finDia
        );

        if (citasDelDoctor.size() >= 8) {
            throw new BadRequestException("El doctor ya tiene 8 citas para este día.");
        }
    }

}
