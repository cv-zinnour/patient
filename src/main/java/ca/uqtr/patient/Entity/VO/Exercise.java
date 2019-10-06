package ca.uqtr.patient.Entity.VO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Exercise {

    private ExerciseType exerciseType;
    private Date date;
    private Duration duration;
}
