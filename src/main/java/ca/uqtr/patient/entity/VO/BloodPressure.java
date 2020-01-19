package ca.uqtr.patient.entity.VO;

import ca.uqtr.patient.entity.MedicalFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blood_pressure", schema = "public")
public class BloodPressure {
    @Id
    @GeneratedValue(generator  = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    UUID id;
    @NotNull
    @Column(name = "value")
    private Double value;
    @Temporal(value= TemporalType.DATE)
    @NotNull
    @Column(name = "date")
    private Date date;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private MedicalFile medicalFile;

    public BloodPressure(@NotNull Double value, @NotNull Date date) {
        this.value = value;
        this.date = date;
    }
}
