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
public class Anthropometry {


    @Column(name = "anthropometry_weight")
    private double weight;
    @Column(name = "anthropometry_height")
    private double height;
    @Column(name = "anthropometry_imc")
    private double imc;
    @Column(name = "anthropometry_waist")
    private double waist;
}
