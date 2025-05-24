package umi.fs.hospital.entities;

import java.util.Collection;

public class Medcin {
    private Long id;
    private String nom;
    private String prenom;
    private String specialite;
    private Collection<RandezVous>rendezVous;
}
