package ca.uqtr.patient.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "recommendation", schema = "public")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Recommendation implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Version
    @Column(name = "version", nullable = false)
    private int version;
    @Column(name = "date_recommendation")
    private Date dateRecommendation ;
    @Column(name = "date_response")
    private Date dateResponse ;
    @Type(type = "jsonb")
    @Column(name = "recommendation", columnDefinition = "jsonb")
    private String recommendation;
    @Type(type = "jsonb")
    @Column(name = "response", columnDefinition = "jsonb")
    private String response;
    @Type(type = "jsonb")
    @Column(name = "barriers_recommendation", columnDefinition = "jsonb")
    private String barriersRecommendation;
    @Type(type = "jsonb")
    @Column(name = "confiance")
    private Integer confiance;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Professional professional;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;


}
