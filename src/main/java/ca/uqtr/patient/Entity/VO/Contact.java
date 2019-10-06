package ca.uqtr.patient.Entity.VO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Contact {

    private Long phone;
    private Email email;
    private Address address;
}
