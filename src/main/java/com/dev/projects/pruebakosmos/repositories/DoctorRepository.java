package com.dev.projects.pruebakosmos.repositories;

import com.dev.projects.pruebakosmos.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
