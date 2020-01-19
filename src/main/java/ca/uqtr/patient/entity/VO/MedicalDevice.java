package ca.uqtr.patient.entity.VO;

import ca.uqtr.patient.entity.MedicalFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_device", schema = "public")
public class MedicalDevice {
    @Id
    @GeneratedValue(generator  = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    UUID id;
    @NotNull
    @Column(name = "type")
    private String type;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private MedicalFile medicalFile;

    public MedicalDevice(@NotNull String type) {
        this.type = type;
    }
}
