package ca.uqtr.patient.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
//@ToString(exclude = "patients")
@NoArgsConstructor
@Entity
@Table(name = "professional", schema = "public")
public class Professional extends BaseEntity {

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "root", nullable = false)
    private boolean root;
    //@ToString.Exclude
    /*@ManyToMany(mappedBy = "professionals",fetch = FetchType.LAZY)
    private Set<Patient> patients = new HashSet<>();*/

    public Professional(String username, boolean root) {
        this.username = username;
        this.root = root;
    }


}
