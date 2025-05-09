package com.dev.projects.pruebakosmos.repositories;

import com.dev.projects.pruebakosmos.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
}
