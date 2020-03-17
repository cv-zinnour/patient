package ca.uqtr.patient.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
    @Column(name = "root", nullable = false)
    private boolean root;
    //@ToString.Exclude
    /*@ManyToMany(mappedBy = "professionals",fetch = FetchType.LAZY)
    private Set<Patient> patients = new HashSet<>();*/
    @JsonManagedReference
    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> recommendations;

    public Professional(UUID id, boolean root) {
        this.id = id;
        this.root = root;
    }


}
