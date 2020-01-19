package ca.uqtr.patient.entity.VO;

import ca.uqtr.patient.entity.BaseEntity;
import ca.uqtr.patient.entity.Patient;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "profession", schema = "public")
public class Profession extends BaseEntity {

    @Column(name = "type")
    private String type;
    @Column(name = "isActive")
    private Boolean isActive;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Profession(String type, Boolean isActive) {
        this.type = type;
        this.isActive = isActive;
    }
}
