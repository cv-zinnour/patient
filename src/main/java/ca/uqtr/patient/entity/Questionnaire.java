package ca.uqtr.patient.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "questionnaire", schema = "public")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Questionnaire extends BaseEntity {

    @Column(name = "type")
    private String type;
    @Type(type = "jsonb")
    @Column(name = "value", columnDefinition = "jsonb")
    private String value;
    @Column(name = "date")
    private Date date ;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

}
