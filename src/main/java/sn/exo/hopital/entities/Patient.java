package sn.exo.hopital.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Patient extends Personne{
    @Column
    private String numDossier;
    @Column
    private String telephone;

    public String getNumDossier() {
        return numDossier;
    }

    public void setNumDossier(String numDossier) {
        this.numDossier = numDossier;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Patient() {
    }

    public Patient(String numDossier, String telephone) {
        this.numDossier = numDossier;
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(numDossier, patient.numDossier) && Objects.equals(telephone, patient.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numDossier, telephone);
    }
    @ManyToMany(mappedBy = "patients")
    private Set<Medecin> medecins = new HashSet<>();
}
