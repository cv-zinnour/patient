package ca.uqtr.patient.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    @Column(name = "token")
    private String token ;
    @Column(name = "token_expiration_date")
    private Timestamp tokenExpirationDate ;

}
