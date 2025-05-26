package umi.fs.hospital.repositories;
import java.util.List;
import umi.fs.hospital.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedcinRepository extends JpaRepository<Medcin, Long > {
    List<Medcin> findAll();
    Medcin findByNom(String nom);
    List<Medcin> findByNomContains(String nom);


}
