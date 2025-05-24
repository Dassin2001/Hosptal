package umi.fs.hospital.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RandezVous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date date;

    private StatusRDV status ;
    @ManyToOne
    private Patient patient;
    @ManyToOne()
    private Medcin medcin;
    @OneToOne(mappedBy = "randezVous")
    private Consultation consultation;
}
