package ca.uqtr.patient.dto.medicalfile.clinical_examination;

import ca.uqtr.patient.dto.medicalfile.clinical_examination.cardiovascular.CardiovascularDto;
import ca.uqtr.patient.entity.ClinicalExamination;
import ca.uqtr.patient.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalExaminationDto {

    private CardiovascularDto cardiovascular;
    private AnthropometryDto anthropometry;
    private SmokingDto smoking;
    private PharmacotherapyDto pharmacotherapy;
    private String date;


    public ClinicalExamination dtoToObj(ModelMapper modelMapper) {
        return modelMapper.map(this, ClinicalExamination.class);
    }

}
