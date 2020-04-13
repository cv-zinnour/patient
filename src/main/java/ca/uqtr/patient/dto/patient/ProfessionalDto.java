package ca.uqtr.patient.dto.patient;

import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Professional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDto {
    private String id;
    private boolean root;

    public Professional dtoToObj(ModelMapper modelMapper) {
        return modelMapper.map(this, Professional.class);
    }

    public UUID getId() {
        if (id != null)
            return UUID.fromString(id);
        else
            return null;
    }

}
