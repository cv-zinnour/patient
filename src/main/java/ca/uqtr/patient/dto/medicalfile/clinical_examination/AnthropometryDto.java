package ca.uqtr.patient.dto.medicalfile.clinical_examination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnthropometryDto {

    private double weight;
    private double height;
    private double imc;
    private double waist;
}
