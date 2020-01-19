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
@Table(name = "exercise", schema = "public")
public class Exercise {
    @Id
    @GeneratedValue(generator  = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    UUID id;
    @NotNull
    @Column(name = "type")
    private String type;
    @Temporal(value= TemporalType.DATE)
    @NotNull
    @Column(name = "date")
    private Date date;
    @NotNull
    @Column(name = "duration")
    private Integer duration;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private MedicalFile medicalFile;

    public Exercise(@NotNull String type, @NotNull Date date, @NotNull Integer duration) {
        this.type = type;
        this.date = date;
        this.duration = duration;
    }
}
