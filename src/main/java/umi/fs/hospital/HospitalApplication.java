package umi.fs.hospital;
import java.util.Date;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import umi.fs.hospital.entities.*;
import umi.fs.hospital.repositories.ConsultationRepository;
import umi.fs.hospital.repositories.MedcinRepository;
import umi.fs.hospital.repositories.PatientRepository;
import umi.fs.hospital.repositories.RendezVousRepository;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
        System.out.println("Hospital Application Started");
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository,
                            ConsultationRepository consultationRepository,
                            MedcinRepository medcinRepository,
                            RendezVousRepository rendezVousRepository) {
        return args -> {

            // Ajouter des patients
            Stream.of("Mohamed", "Hassan", "Najat").forEach(name -> {
                Patient patient = new Patient();
                patient.setNom(name);
                patient.setMalade(false);
                patient.setDateNaissance(new Date());
                patientRepository.save(patient);
            });

            // Ajouter des médecins
            Stream.of("Aymane", "Hanane", "Yassmine").forEach(name -> {
                Medcin medcin = new Medcin();
                medcin.setNom(name);
                medcin.setEmail(name + "@gmail.com");
                medcin.setSpecialite(Math.random() > 0.5 ? "Cardiologie" : "Dentisterie");
                medcinRepository.save(medcin);
            });

            // Récupérer patient et médecin
            Patient patient = patientRepository.findById(1L).orElse(null);
            Patient patient1 = patientRepository.findByNom("Mohamed");
            Medcin medcin1 = medcinRepository.findByNom("Yassmine");

            // Créer un rendez-vous
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedcin(medcin1);
            rendezVous.setPatient(patient);
            rendezVousRepository.save(rendezVous);


            RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);

            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de La consultation ....");
            consultationRepository.save(consultation);

            // Consulter tous les patients
            System.out.println("\nConsulter tous les patients ");
            List<Patient> patients = patientRepository.findAll();
            patients.forEach(p -> {
                System.out.println("Patient : " + p.getId() + " ==> " + p.getNom());
            });

            // Consulter un patient par ID
            System.out.println("\nConsulter un patient");
            Patient patien = patientRepository.findById(2L).orElse(null);
            System.out.println("Patient avec id 1 : " + patien.getId() +" ==> "+ patien.getNom() );



            //  Chercher des patients (nom contient 'a')
            System.out.println("\n# Chercher des patients ");
            List<Patient> searchResults = patientRepository.findByNomContains("a");
            searchResults.forEach(p -> System.out.println("Patient trouvé : " + p.getNom()));

            // Mettre à jour un patient
            System.out.println("\nMettre à jour un patient");
            if (patient != null) {
                patient.setMalade(true);
                patient.setNom("Mohamed Updated");
                patientRepository.save(patient);
                System.out.println("Patient mis à jour : " + patient.getNom() + ", Malade: " + patient.isMalade());
            } else {
                System.out.println("Patient non trouvé !");
            }


            //  Supprimer un patient
            System.out.println("\nSupprimer un patient ");
            Long idToDelete = 2L;
            if (patientRepository.existsById(idToDelete)) {
                patientRepository.deleteById(idToDelete);
                System.out.println("Patient avec ID " + idToDelete + " supprimé.");
            } else {
                System.out.println("Patient avec ID " + idToDelete + " introuvable.");
            }

        };


    }
}
