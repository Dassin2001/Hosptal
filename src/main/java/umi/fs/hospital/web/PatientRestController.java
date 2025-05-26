package umi.fs.hospital.entities.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import umi.fs.hospital.entities.*;
import umi.fs.hospital.repositories.PatientRepository;

import java.util.List;

public class PatientRestController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/patients")
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }
}
