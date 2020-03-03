package ca.uqtr.patient.dto;

import ca.uqtr.patient.dto.medicalfile.SocioDemographicVariablesDto;
import ca.uqtr.patient.dto.medicalfile.clinical_examination.ClinicalExaminationDto;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.MedicalFileHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalFileDto {

    private String id;
    private String patient;
    private List<MedicalFileHistoryDto> medicalFileHistory = new ArrayList<>();
    private List<ClinicalExaminationDto> clinicalExamination = new ArrayList<>();
    private String creationDate ;
    private List<LipidProfileDto> lipidProfiles = new ArrayList<>();

    public MedicalFile dtoToObj(ModelMapper modelMapper) {
        return modelMapper.map(this, MedicalFile.class);
    }

    public UUID getId() {
        if (id != null)
            return UUID.fromString(id);
        else
            return null;
    }
}
