package umi.fs.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import umi.fs.hospital.entities.RendezVous;
import umi.fs.hospital.entities.StatusRDV;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, String> {
    List<RendezVous> findByStatus(StatusRDV status);
}
