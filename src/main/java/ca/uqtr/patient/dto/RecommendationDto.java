package ca.uqtr.patient.dto;

import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Questionnaire;
import ca.uqtr.patient.entity.Recommendation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationDto {
    private int id;
    private String patientId;
    private String recommendation;


    public Recommendation dtoToObj(ModelMapper modelMapper) {
        return modelMapper.map(this, Recommendation.class);
    }

}
