package ca.uqtr.patient.dto;

import ca.uqtr.patient.entity.BaseEntity;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.VO.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.jasypt.util.text.AES256TextEncryptor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalFileDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String code;
    private String patient;
    private List<Allergy> allergies;
    private List<BloodPressure> bloodPressure;
    private List<Condition> condition;
    private List<Exercise> exercises;
    private List<MedicalDevice> medicalDevices;
    private List<Medicament> medicaments;


    public MedicalFile medicalFile2Dto(ModelMapper modelMapper) {
        return modelMapper.map(this, MedicalFile.class);
    }

    public void setPatient(String patient) {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("epod-uqtr");
        this.patient = textEncryptor.encrypt(patient);
    }

}
