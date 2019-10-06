package ca.uqtr.patient.Entity.VO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BloodPressure {

    private Double value;
    private Date date;
}
