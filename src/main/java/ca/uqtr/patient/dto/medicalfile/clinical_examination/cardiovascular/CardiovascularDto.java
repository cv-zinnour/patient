package ca.uqtr.patient.dto.medicalfile.clinical_examination.cardiovascular;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardiovascularDto {

    private HeartRateDto heartRate;
    private BloodPressureDto bloodPressure;

}
