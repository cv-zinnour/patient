package ca.uqtr.patient.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
@Table(name = "expert", schema = "public")
public class Expert extends BaseEntity {

    @Column(name = "expert_id", nullable = false)
    private UUID expertId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Expert(UUID expertId, String firstName, String lastName) {
        this.expertId = expertId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
