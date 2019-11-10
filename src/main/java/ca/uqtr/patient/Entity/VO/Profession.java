package ca.uqtr.patient.Entity.VO;

import ca.uqtr.patient.Entity.BaseEntity;
import ca.uqtr.patient.Entity.Patient;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

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
