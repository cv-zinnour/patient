package ca.uqtr.patient.dto.medicalfile.clinical_examination.cardiovascular;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeartRateDto {

    private int value;
    private Boolean regular;
}
