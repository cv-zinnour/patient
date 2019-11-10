package ca.uqtr.patient.Entity;

import ca.uqtr.patient.Entity.VO.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.*;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "medical_file", schema = "public")
public class MedicalFile extends BaseEntity {

    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "patient")
    private String patient;

    @OneToMany(mappedBy="medicalFile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Allergy> allergies;
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<BloodPressure> bloodPressure;
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Condition> condition;
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Exercise> exercises;
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<MedicalDevice> medicalDevices;
    @OneToMany(mappedBy="medicalFile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
