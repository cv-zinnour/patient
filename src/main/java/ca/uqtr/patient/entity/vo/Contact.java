package ca.uqtr.patient.entity.vo;

import ca.uqtr.patient.entity.Patient;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contact", schema = "public")
public class Contact {
    @Id
    private UUID id;
    @Version
    @Column(name = "version", nullable = false)
    private int version;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "email")
    private String email;
    @Embedded
    private Address address;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Patient patient;

    public Contact(Long phone, String email, Address address) {
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
