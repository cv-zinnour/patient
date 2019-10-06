package ca.uqtr.patient.Entity;

import ca.uqtr.patient.Entity.VO.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "medical_file", schema = "public")
public class MedicalFile extends BaseEntity {


    @Column(name = "allergies")
    private List<Allergy> allergies;
    @Column(name = "blood_pressure")
    private List<BloodPressure> bloodPressure;
    @Column(name = "condition")
    private List<Condition> condition;
    @Column(name = "exercises")
    private List<Exercise> exercises;
    @Column(name = "medical_devices")
    private List<MedicalDevice> medicalDevices;
    @Column(name = "medicaments")
    private List<Medicament> medicaments;
}
