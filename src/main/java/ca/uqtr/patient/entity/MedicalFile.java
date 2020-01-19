package ca.uqtr.patient.entity;

import ca.uqtr.patient.entity.VO.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.jasypt.util.text.AES256TextEncryptor;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "medical_file", schema = "public")
public class MedicalFile extends BaseEntity {

    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "patient")
    private String patient;

    @JsonBackReference
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Allergy> allergies;
    @JsonBackReference
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<BloodPressure> bloodPressure;
    @JsonBackReference
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Condition> condition;
    @JsonBackReference
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Exercise> exercises;
    @JsonBackReference
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<MedicalDevice> medicalDevices;
    @JsonBackReference
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Medicament> medicaments;



    public MedicalFile(String code) {
        this.code = code;
    }


    public void setPatient(String patient) {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("epod-uqtr");
        this.patient = textEncryptor.encrypt(patient);
    }

    public String getPatient() {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("epod-uqtr");
        return textEncryptor.decrypt(patient);
    }
}
