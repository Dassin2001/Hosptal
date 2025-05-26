package umi.fs.hospital.service;

import umi.fs.hospital.entities.Consultation;
import umi.fs.hospital.entities.Medcin;
import umi.fs.hospital.entities.Patient;
import umi.fs.hospital.entities.RendezVous;

public interface IHospitalService {
    public Patient savePatient(Patient patient);
    public Medcin saveMedcin(Medcin medcin);
    public RendezVous saveRDV(RendezVous rendezVous);
    public Consultation saveConsultation(Consultation consultation);}
