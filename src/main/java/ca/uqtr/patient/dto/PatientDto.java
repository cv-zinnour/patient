package ca.uqtr.patient.dto;

import ca.uqtr.patient.entity.Expert;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.VO.Contact;
import ca.uqtr.patient.entity.VO.Profession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthday;
    private Contact contact;
    private MedicalFile medicalFile;
    private List<Profession> profession = new ArrayList<>();
    private List<Expert> expert = new ArrayList<>();
    private Boolean isActive;


    public Patient patient2Dto(ModelMapper modelMapper) {
        return modelMapper.map(this, Patient.class);
    }
    
}
