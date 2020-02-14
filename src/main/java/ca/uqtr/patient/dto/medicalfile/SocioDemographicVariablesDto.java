package ca.uqtr.patient.dto.medicalfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocioDemographicVariablesDto {

    private String age;
    private String civilStatus;
    private String familyIncome;
    private String jobStatus;
    private String education;
    private LivingEnvironmentDto livingEnvironment;

}
