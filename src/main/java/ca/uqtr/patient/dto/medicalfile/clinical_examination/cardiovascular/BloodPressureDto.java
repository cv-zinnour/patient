package ca.uqtr.patient.dto.medicalfile.clinical_examination.cardiovascular;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodPressureDto {

    private int bloodPressureRightHandDiastolique;
    private int bloodPressureLeftHandDiastolique;
    private int bloodPressureRightHandSystolique;
    private int bloodPressureLeftHandSystolique;

}
