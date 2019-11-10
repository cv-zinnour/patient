package ca.uqtr.patient.Entity.VO;

import ca.uqtr.patient.Entity.MedicalFile;
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
@Table(name = "medicament", schema = "public")
public class Medicament {
    @Id
    @GeneratedValue(generator  = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    UUID id;
    @NotNull
    @Column(name = "name")
    private String name;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private MedicalFile medicalFile;

    public Medicament(@NotNull String name) {
        this.name = name;
    }
}
