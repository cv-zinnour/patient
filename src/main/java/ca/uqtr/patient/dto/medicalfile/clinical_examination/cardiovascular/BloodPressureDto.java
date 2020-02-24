package ca.uqtr.patient.dto.medicalfile.clinical_examination.cardiovascular;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodPressureDto {

    private int bloodPressureRightHand;
    private int bloodPressureLeftHand;
}
