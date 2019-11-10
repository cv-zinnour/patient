package ca.uqtr.patient.Entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    @Column(name = "street")
    private String street;
    @Column(name = "street_number")
    private Integer streetNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "province")
    private String province;

}
