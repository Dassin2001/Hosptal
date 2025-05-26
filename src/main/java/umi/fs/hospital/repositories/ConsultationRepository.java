package umi.fs.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import umi.fs.hospital.entities.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
