package ca.uqtr.patient.entity;

import ca.uqtr.patient.entity.vo.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.jasypt.util.text.AES256TextEncryptor;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "medical_file", schema = "public")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class MedicalFile extends BaseEntity {

    @Column(name = "patient")
    private String patient;
    @Type(type = "jsonb")
    @Column(name = "socio_demographic_variables", columnDefinition = "jsonb")
    private String socioDemographicVariables;
    @Type(type = "jsonb")
    @Column(name = "antecedents", columnDefinition = "jsonb")
    private String antecedents;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ClinicalExamination> clinicalExamination = new ArrayList<>();
    @Column(name = "creation_date")
    private Date creationDate ;

    public void setPatient(String patient) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword("pod-uqtr");
        //this.patient = encryptor.encrypt(patient);
        this.patient = patient;
    }


}
