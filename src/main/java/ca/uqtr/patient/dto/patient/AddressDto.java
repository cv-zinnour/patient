package ca.uqtr.patient.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private String street;
    private Integer street_number;
    private String city;
    private String postal_code;
    private String province;
}
