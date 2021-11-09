package ca.uqtr.patient.dto;

import ca.uqtr.patient.dto.patient.ContactDto;
import ca.uqtr.patient.dto.patient.FamilyDoctorDto;
import ca.uqtr.patient.dto.patient.PharmacyDto;
import ca.uqtr.patient.dto.patient.ProfessionalDto;
import ca.uqtr.patient.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualQuestionnaireDto {

    @Nullable
    private String patientId;
    @Nullable
    private String fileNumber;
    @Nullable
    private String initial;

    private String socioDemographicVariables;

    private String medicalFileHistory;


}
