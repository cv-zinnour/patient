package ca.uqtr.patient.entity.vo.clinicalexamination.cardiovascular;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Cardiovascular {

    @Embedded
    private HeartRate heartRate;
    @Embedded
    private BloodPressure bloodPressure;



}
