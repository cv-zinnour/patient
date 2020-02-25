package ca.uqtr.patient.dto;

import ca.uqtr.patient.entity.LipidProfile;
import ca.uqtr.patient.entity.MedicalFile;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class LipidProfileDto {
    private String id;
    private double ldl;
    private double hdl;
    private double nohdl;
    private double triglyceride;
    private double hba1c;

    public LipidProfile dtoToObj(ModelMapper modelMapper) {
        return modelMapper.map(this, LipidProfile.class);
    }

}
