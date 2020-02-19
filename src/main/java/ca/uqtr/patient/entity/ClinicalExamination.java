package ca.uqtr.patient.entity;

import ca.uqtr.patient.entity.vo.clinicalexamination.Anthropometry;
import ca.uqtr.patient.entity.vo.clinicalexamination.Smoking;
import ca.uqtr.patient.entity.vo.clinicalexamination.cardiovascular.Cardiovascular;
import ca.uqtr.patient.entity.vo.clinicalexamination.Pharmacotherapy;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "clinical_examination", schema = "public")
public class ClinicalExamination extends BaseEntity{

    @Embedded
    private Cardiovascular cardiovascular;
    @Embedded
    private Anthropometry anthropometry;
    @Embedded
    private Smoking smoking;
    @Embedded
    private Pharmacotherapy pharmacotherapy;
    @Column(name = "date")
    private Date date;
    /*@ManyToOne(fetch = FetchType.LAZY)
    private MedicalFile medicalFile;*/
}
