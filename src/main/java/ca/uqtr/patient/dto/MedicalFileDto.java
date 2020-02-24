package ca.uqtr.patient.dto;

import ca.uqtr.patient.dto.medicalfile.AntecedentsDto;
import ca.uqtr.patient.dto.medicalfile.SocioDemographicVariablesDto;
import ca.uqtr.patient.dto.medicalfile.clinical_examination.ClinicalExaminationDto;
import ca.uqtr.patient.entity.LipidProfile;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalFileDto {

    private String id;
    private String patient;
    private SocioDemographicVariablesDto socioDemographicVariables;
    private List<AntecedentsDto> antecedents = new ArrayList<>();
    private List<ClinicalExaminationDto> clinicalExamination = new ArrayList<>();
    private String creationDate ;
    private List<LipidProfileDto> lipidProfiles = new ArrayList<>();

    public MedicalFile dtoToObj(ModelMapper modelMapper) {
        return modelMapper.map(this, MedicalFile.class);
    }

}
