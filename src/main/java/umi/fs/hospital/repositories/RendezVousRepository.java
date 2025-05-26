package umi.fs.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import umi.fs.hospital.entities.RendezVous;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
}
