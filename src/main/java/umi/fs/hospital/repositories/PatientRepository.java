package umi.fs.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import umi.fs.hospital.entities.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByNom(String nom);
    List<Patient> findByNomContains(String nom);//


}
