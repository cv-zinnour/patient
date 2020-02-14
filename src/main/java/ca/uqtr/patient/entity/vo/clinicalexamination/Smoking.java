package ca.uqtr.patient.entity.vo.clinicalexamination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Smoking {

    @Column(name = "smoking_type")
    private String type;
    @Column(name = "smoking_number_cigarettes")
    private int numberCigarettes; //per day

}
