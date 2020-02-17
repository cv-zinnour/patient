package ca.uqtr.patient.entity.vo;

import ca.uqtr.patient.entity.BaseEntity;
import ca.uqtr.patient.entity.Patient;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contact", schema = "public")
public class Contact implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
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
    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Patient patient;

}
