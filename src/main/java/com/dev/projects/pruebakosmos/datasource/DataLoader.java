package com.dev.projects.pruebakosmos.datasource;

import com.dev.projects.pruebakosmos.entities.Doctor;
import com.dev.projects.pruebakosmos.entities.Office;
import com.dev.projects.pruebakosmos.repositories.DoctorRepository;
import com.dev.projects.pruebakosmos.repositories.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final OfficeRepository officeRepository;

    @Autowired
    public DataLoader(DoctorRepository doctorRepository, OfficeRepository officeRepository) {
        this.doctorRepository = doctorRepository;
        this.officeRepository = officeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var Doctors = List.of(
                new Doctor(null, "Luis", "Ramos", "Fernandez", "Medicina Interna"),
                new Doctor(null, "Ana", "Martinez", "Lopez", "Medicina Interna"),
                new Doctor(null, "Jose", "Garcia", "Gonzalez", "Medicina Interna"),
                new Doctor(null, "Maria", "Gonzalez", "Garcia", "Medicina Interna"),
                new Doctor(null, "Jose", "Garcia", "Gonzalez", "Medicina Interna")
        );

        doctorRepository.saveAll(Doctors);

        var offices = List.of(
                new Office(null, 101, 1),
                new Office(null, 102, 1),
                new Office(null, 201, 2)
        );

        officeRepository.saveAll(offices);
    }
}
