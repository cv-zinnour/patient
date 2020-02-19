package ca.uqtr.patient.entity;

import ca.uqtr.patient.dto.medicalfile.SocioDemographicVariablesDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "medical_file_history", schema = "public")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class MedicalFileHistory extends BaseEntity {


    @Column(name = "date")
    private Date date ;
    @Type(type = "jsonb")
    @Column(name = "antecedents", columnDefinition = "jsonb")
    private String antecedents;

    public MedicalFileHistory(Date date, String antecedents) {
        this.date = date;
        this.antecedents = antecedents;
    }
}
