package ca.uqtr.patient.Entity;

import ca.uqtr.patient.Entity.VO.Contact;
import ca.uqtr.patient.Entity.VO.Profession;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patient", schema = "public")
public class Patient extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "midlle_name")
    private String midlleName;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(value= TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "contact")
    private Contact contact;
    @Column(name = "profession")
    private List<Profession> profession;
    @Column(name = "medical_file")
    private MedicalFile medicalFile;
    @Column(name = "expert")
    private List<Expert> expert;
    @Column(name = "is_active")
    private Boolean isActive;

}
