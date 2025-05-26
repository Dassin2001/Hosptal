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
import umi.fs.hospital.service.IHospitalService;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
        System.out.println("Hospital Application Started");
    }

    @Bean
    CommandLineRunner start(IHospitalService iHospitalService,
                            PatientRepository patientRepository,
                            RendezVousRepository rendezVousRepository,
                            ConsultationRepository consultationRepository,
                            MedcinRepository medcinRepository) {
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
            Stream.of("Aymane", "Hanane", "Yassmine","Ali","Mustapha").forEach(name -> {
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
            rendezVous.setId(UUID.randomUUID().toString()); // Générer un ID unique de type String
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedcin(medcin1);
            rendezVous.setPatient(patient);

            rendezVousRepository.save(rendezVous);

            String rdvId = rendezVous.getId();

            RendezVous rendezVous1 = rendezVousRepository.findById(rdvId).orElse(null);
            if (rendezVous1 != null) {
                System.out.println("Rendez-vous créé avec ID : " + rendezVous1.getId());
            } else {
                System.out.println("Échec de la création du rendez-vous.");
            }


            //RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);

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


            // Consulter tous les médecins
            System.out.println("\nConsulter tous les médecins ");
            List<Medcin> medcins = medcinRepository.findAll();
            medcins.forEach(m -> {
                System.out.println("Medcin : " + m.getId() + " ==> " + m.getNom() + " (" + m.getSpecialite() + ")");
            });

            // Consulter un médecin par ID
            System.out.println("\nConsulter un médecin par ID");
            Medcin medcinById = medcinRepository.findById(1L).orElse(null);
            if (medcinById != null) {
                System.out.println("Médecin avec ID 1 : " + medcinById.getNom() + ", Email: " + medcinById.getEmail());
            }

            // Supprimer un médecin
            System.out.println("\nSupprimer un médecin");
            Long medcinIdToDelete = 2L;
            if (medcinRepository.existsById(medcinIdToDelete)) {
                medcinRepository.deleteById(medcinIdToDelete);
                System.out.println("Médecin avec ID " + medcinIdToDelete + " supprimé.");
            } else {
                System.out.println("Médecin avec ID " + medcinIdToDelete + " introuvable.");
            }
            System.out.println("\nConsulter une consultation");
            Consultation cons = consultationRepository.findById(1L).orElse(null);
            if (cons != null) {
                System.out.println("Consultation ID: " + cons.getId() + ", Date: " + cons.getDateConsultation() + ", Rapport: " + cons.getRapport());
            }
            System.out.println("\nChercher des consultations contenant le mot 'Rapport'");
            List<Consultation> consultations = consultationRepository.findByRapportContains("Rapport");
            consultations.forEach(c ->
                    System.out.println("Consultation trouvée : ID " + c.getId() + ", Rapport : " + c.getRapport())
            );
            System.out.println("\nMettre à jour une consultation");
            Consultation consultationToUpdate = consultationRepository.findById(1L).orElse(null);
            if (consultationToUpdate != null) {
                consultationToUpdate.setRapport("Rapport mis à jour après traitement...");
                consultationRepository.save(consultationToUpdate);
                System.out.println("Consultation mise à jour : " + consultationToUpdate.getRapport());
            }

            System.out.println("\nChercher les rendez-vous avec statut PENDING");
            List<RendezVous> rdvs = rendezVousRepository.findByStatus(StatusRDV.PENDING);
            rdvs.forEach(r ->
                    System.out.println("RDV ID: " + r.getId() + ", Patient: " + r.getPatient().getNom() + ", Status: " + r.getStatus())
            );
            System.out.println("\nMettre à jour un rendez-vous");

            RendezVous rdvToUpdate = rendezVousRepository.findById(rdvId).orElse(null);
            if (rdvToUpdate != null) {
                rdvToUpdate.setStatus(StatusRDV.DONE);
                rendezVousRepository.save(rdvToUpdate);
                System.out.println("Rendez-vous mis à jour : " + rdvToUpdate.getStatus());
            } else {
                System.out.println("Aucun rendez-vous trouvé avec l'ID : " + rdvId);
            }

        };



    }
}
