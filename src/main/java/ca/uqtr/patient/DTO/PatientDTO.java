package ca.uqtr.patient.DTO;

import ca.uqtr.patient.Entity.Expert;
import ca.uqtr.patient.Entity.MedicalFile;
import ca.uqtr.patient.Entity.VO.Contact;
import ca.uqtr.patient.Entity.VO.Profession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String middleName;
    @NotNull
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private Date birthday;

    @NotNull
    @NotEmpty
    private Contact contact;
    @NotNull
    @NotEmpty
    private MedicalFile medicalFile;

    @NotNull
    @NotEmpty
    private List<Profession> profession = new ArrayList<>();
    @NotNull
    @NotEmpty
    private List<Expert> expert = new ArrayList<>();

    @Column(name = "is_active")
    private Boolean isActive;

    
    
}
