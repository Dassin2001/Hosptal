package umi.fs.hospital.entities;

import java.util.Collection;
import java.util.Date;

public class Patient {
    private  Long id;
    private  String nom;
    private Date dateNaissance;
    private boolean malade;
    private Collection <RandezVous> randezVous;
}
