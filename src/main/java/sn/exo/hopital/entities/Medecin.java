package sn.exo.hopital.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;


@Entity
@NamedQuery(name = "listMedecins", query = "SELECT u FROM Medecin u ORDER BY u.id ASC")
public class Medecin extends Personne{
    @Column
    private String grade;
    @Column
    private int salaire;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public Medecin() {
    }

    public Medecin(String grade, int salaire) {
        this.grade = grade;
        this.salaire = salaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medecin medecin = (Medecin) o;
        return salaire == medecin.salaire && Objects.equals(grade, medecin.grade) && Objects.equals(patients, medecin.patients) && Objects.equals(specialite, medecin.specialite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, salaire, patients, specialite);
    }

    @ManyToMany
    @JoinTable(
            name = "medecin_patient",
            joinColumns = @JoinColumn(name = "medecin_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<Patient> patients = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "specialite_id")
    private Specialite specialite;

}
