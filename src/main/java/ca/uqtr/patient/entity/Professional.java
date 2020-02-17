package ca.uqtr.patient.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
@Table(name = "professional", schema = "public")
public class Professional implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Version
    @Column(name = "version", nullable = false)
    private int version;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "root", nullable = false)
    private boolean root;
    //@ToString.Exclude
    @ManyToMany(mappedBy = "professionals")
    private Set<Patient> patients = new HashSet<>();

    public Professional(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
