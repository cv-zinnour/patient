package ca.uqtr.patient.Entity;

import ca.uqtr.patient.Entity.VO.Contact;
import ca.uqtr.patient.Entity.VO.Profession;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "patient", schema = "public")
public class Patient extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(value= TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;

  /*  @OneToOne(mappedBy="patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Contact contact;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "id")
    private List<Profession> profession;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "id")
    private List<Expert> expert;*/

    @Column(name = "is_active")
    private Boolean isActive;


    public Patient(String firstName, String middleName, String lastName, Date birthday, Boolean isActive) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.isActive = isActive;
    }

}
