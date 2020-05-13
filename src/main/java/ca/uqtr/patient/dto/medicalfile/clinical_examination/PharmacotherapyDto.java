package ca.uqtr.patient.dto.medicalfile.clinical_examination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacotherapyDto {

    private String cardiovascular;
    private String dyslipidemia;
    private String diabetes;
    private String other;
}
