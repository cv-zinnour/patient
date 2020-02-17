package ca.uqtr.patient.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
//@ToString(exclude = "patients")
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
    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patients = new ArrayList<>();

    public Professional(UUID id, String firstName, String lastName, boolean root) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.root = root;
    }


}
