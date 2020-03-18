package ca.uqtr.patient.dto;

import ca.uqtr.patient.dto.ErrorDto;
import ca.uqtr.patient.dto.medicalfile.SocioDemographicVariablesDto;
import ca.uqtr.patient.dto.patient.ContactDto;
import ca.uqtr.patient.dto.patient.FamilyDoctorDto;
import ca.uqtr.patient.dto.patient.PharmacyDto;
import ca.uqtr.patient.dto.patient.ProfessionalDto;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Questionnaire;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    @Nullable
    private String id;
    @Nullable
    private String fileNumber;
    @Nullable
    private String firstName;
    @Nullable
    private String lastName;
    @Nullable
    private Date birthday;
    @Nullable
    private String motherName;
    @Nullable
    private ContactDto contact;
    @Nullable
    private List<FamilyDoctorDto> familyDoctor = new ArrayList<>();
    @Nullable
    private List<PharmacyDto> pharmacy = new ArrayList<>();
    @Nullable
    private ProfessionalDto professional;
    @Nullable
    private Boolean isActive;
    private String questionnaireToken ;
    private Date questionnaireTokenExpirationDate ;
    @Nullable
    MedicalFileDto medicalFile;
    private String loginCode;
    private SocioDemographicVariablesDto socioDemographicVariables;
    //private List<Questionnaire> questionnaires = new ArrayList<>();
    private boolean hasBREQ;


    public Patient dtoToObj(ModelMapper modelMapper) {
        return modelMapper.map(this, Patient.class);
    }

    public UUID getId() {
        if (id != null)
            return UUID.fromString(id);
        else
            return null;
    }
}
