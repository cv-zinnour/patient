package ca.uqtr.patient.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDto {
    private String id;
    private String firstName;
    private String lastName;
}
