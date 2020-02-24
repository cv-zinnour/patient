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

    private List<String> cardiovascular = new ArrayList<>();
    private List<String> dyslipidemia = new ArrayList<>();
    private List<String> diabetes = new ArrayList<>();
    private List<String> other = new ArrayList<>();
}
