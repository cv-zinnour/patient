package ca.uqtr.patient.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {

    private String id;
    private Long phone;
    private String email;
    private AddressDto address;

}
