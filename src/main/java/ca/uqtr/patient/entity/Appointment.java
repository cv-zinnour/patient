package ca.uqtr.patient.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@ToString
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Table(name = "appointment", schema = "public")
public class Appointment extends BaseEntity {

    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "appointment_date")
    private Date appointmentDate;
    @Column(name = "jpaq_email")
    private boolean jpaqEmail;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Professional professional;
}
