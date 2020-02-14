package ca.uqtr.patient.entity.vo.clinicalexamination.cardiovascular;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BloodPressure {

    @Column(name = "cardiovascular_blood_pressure_right_hand")
    private int bloodPressureRightHand;
    @Column(name = "cardiovascular_blood_pressure_left_hand")
    private int bloodPressureLeftHand;
}
