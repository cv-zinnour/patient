package ca.uqtr.patient.dto.medicalfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivingEnvironmentDto {
    private String zone;
    private Boolean type;
    private Boolean services;
}
