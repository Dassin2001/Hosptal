package umi.fs.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umi.fs.hospital.entities.Consultation;
import umi.fs.hospital.entities.RendezVous;
import umi.fs.hospital.entities.StatusRDV;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByRapportContains(String rapport);
    @Query("SELECT c FROM Consultation c WHERE c.rendezVous.status = :status")
    List<Consultation> findByStatus(@Param("status") StatusRDV status);

}
