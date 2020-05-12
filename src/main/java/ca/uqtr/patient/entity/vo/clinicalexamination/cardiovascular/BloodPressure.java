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

    @Column(name = "blood_pressure_right_hand_diastolique")
    private int bloodPressureRightHandDiastolique;
    @Column(name = "blood_pressure_left_hand_diastolique")
    private int bloodPressureLeftHandDiastolique;
    @Column(name = "blood_pressure_right_hand_systolique")
    private int bloodPressureRightHandSystolique;
    @Column(name = "blood_pressure_left_hand_systolique")
    private int bloodPressureLeftHandSystolique;
}
