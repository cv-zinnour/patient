package ca.uqtr.patient.dto;

import ca.uqtr.patient.dto.patient.ProfessionalDto;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Questionnaire;
import ca.uqtr.patient.entity.Recommendation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationDto {
    private int id;
    @JsonIgnore
    private PatientDto patient;
    @JsonIgnore
    private ProfessionalDto professional;
    private String recommendation;
    private String response;
    private Date dateRecommendation ;
    private Date dateResponse ;


    public Recommendation dtoToObj(ModelMapper modelMapper) {
        return modelMapper.map(this, Recommendation.class);
    }




}
