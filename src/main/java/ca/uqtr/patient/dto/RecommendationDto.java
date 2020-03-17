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
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationDto {
    private int id;
    private String patientId;
    private String professionalId;
    private String recommendation;
    private String response;


    public Recommendation dtoToObj(ModelMapper modelMapper) {
        return modelMapper.map(this, Recommendation.class);
    }


    public UUID getPatientId() {
        if (patientId != null)
            return UUID.fromString(patientId);
        else
            return null;
    }

    public UUID getProfessionalId() {
        if (professionalId != null)
            return UUID.fromString(professionalId);
        else
            return null;
    }

}
