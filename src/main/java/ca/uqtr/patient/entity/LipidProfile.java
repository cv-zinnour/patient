package ca.uqtr.patient.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "lipid_profile", schema = "public")
public class LipidProfile extends BaseEntity{
    @Column(name = "ldl")
    private String ldl;
    @Column(name = "hdl")
    private String hdl;
    @Column(name = "nohdl")
    private String nohdl;
    @Column(name = "triglyceride")
    private String triglyceride;
    @Column(name = "hba1c")
    private String hba1c;


}
