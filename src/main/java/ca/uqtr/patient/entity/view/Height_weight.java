package ca.uqtr.patient.entity.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;
@ToString
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Immutable
public class Height_weight implements Serializable {

    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator  = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "patient_id")
    private String patientId;
    @Column(name = "height")
    private String height;
    @Column(name = "weight")
    private String weight;
}
