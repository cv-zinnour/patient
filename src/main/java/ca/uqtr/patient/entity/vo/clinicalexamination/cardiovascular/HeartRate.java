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
public class HeartRate {

    @Column(name = "cardiovascular_heart_rate_value")
    private int value;
    @Column(name = "cardiovascular_heart_rate_regularity")
    private Boolean regular;
}
