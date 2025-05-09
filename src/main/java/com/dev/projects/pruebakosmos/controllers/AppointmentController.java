package com.dev.projects.pruebakosmos.controllers;

import com.dev.projects.pruebakosmos.dto.requests.AppointmentRequestDTO;
import com.dev.projects.pruebakosmos.dto.responses.ResponseGeneric;
import com.dev.projects.pruebakosmos.entities.Appointment;
import com.dev.projects.pruebakosmos.services.AppointmentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final Logger log = LoggerFactory.getLogger(AppointmentController.class.getName());
    private final AppointmentsService appointmentsService;

    @Autowired
    public AppointmentController(AppointmentsService appointmentsService) {
        this.appointmentsService = appointmentsService;
    }

    @PostMapping()
    public ResponseEntity<?> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        log.info("Inicia creacion de cita: {}", appointmentRequestDTO);

        ResponseGeneric response = appointmentsService.createAppointment(appointmentRequestDTO);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarCita(@PathVariable Long id) {
        log.info("Cancelando cita con id: {}", id);

        ResponseGeneric response = appointmentsService.cancelarCita(id);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> consultarCitasPorDoctor(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        ResponseGeneric response = appointmentsService.consultarCitasPorDoctorYFecha(doctorId, fecha);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{idCita}")
    public ResponseEntity<ResponseGeneric> actualizarCita(@PathVariable Long idCita) {
        ResponseGeneric response = appointmentsService.actualizarCita(idCita);

        response.setStatusCode(200);
        response.setMessageCode("Cita actualizada con éxito");
        response.setData(response.getData());

        return ResponseEntity.ok(response);
    }

}
