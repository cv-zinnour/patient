package ca.uqtr.patient.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "lipid_profile", schema = "public")
public class LipidProfile extends BaseEntity{
    @Column(name = "ldl")
    private double ldl;
    @Column(name = "hdl")
    private double hdl;
    @Column(name = "nohdl")
    private double nohdl;
    @Column(name = "triglyceride")
    private double triglyceride;
    @Column(name = "hba1c")
    private double hba1c;
    @Column(name = "date")
    private Date date ;
    @Column(name = "glucose_jeun")
    private double glucoseJeun;
    @Column(name = "glucose_aleatoire")
    private double glucoseAleatoire;


}
