package ca.uqtr.patient.dto.medicalfile.clinical_examination;

import ca.uqtr.patient.dto.medicalfile.clinical_examination.cardiovascular.CardiovascularDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalExaminationDto {

    private CardiovascularDto cardiovascular;
    private AnthropometryDto anthropometry;
    private SmokingDto smoking;
    private PharmacotherapyDto pharmacotherapy;
    private String date;

}
