package ca.uqtr.patient.dto;

import ca.uqtr.patient.entity.ClinicalExamination;
import ca.uqtr.patient.entity.Questionnaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireDto {
    private String patientId;
    private String type;
    private String value;
    private String token ;
    private String tokenExpirationDate ;
    private ErrorDto error;

    public Questionnaire dtoToObj(ModelMapper modelMapper) {
        return modelMapper.map(this, Questionnaire.class);
    }

}
