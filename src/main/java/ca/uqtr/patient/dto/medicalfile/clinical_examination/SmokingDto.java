package ca.uqtr.patient.dto.medicalfile.clinical_examination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmokingDto {

    private String type;
    private int numberCigarettes; //per day
}
