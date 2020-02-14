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
public class Pharmacotherapy {

    @Column(name = "pharmacotherapy_cardiovascular")
    private String cardiovascular;
    @Column(name = "pharmacotherapy_dyslipidemia")
    private String dyslipidemia;
    @Column(name = "pharmacotherapy_diabetes")
    private String diabetes;
    @Column(name = "pharmacotherapy_other")
    private String other;
}
