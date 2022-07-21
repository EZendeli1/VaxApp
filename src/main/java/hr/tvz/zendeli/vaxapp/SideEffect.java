package hr.tvz.zendeli.vaxapp;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SIDEEFFECTS")
public class SideEffect implements Serializable {
    @Id
    @Column(name="ID")
    @GeneratedValue
    Long ID;
    @Column(name="frequency")
    Float frequency;

 /*   @Column(name="vaccineID")
    Integer vaccineID;*/


    public SideEffect(Long ID, Float frequency, Integer vaccineID, String description, String longDescription) {
        this.ID = ID;
        this.frequency = frequency;
      /*  this.vaccineID = vaccineID;*/
        this.description = description;
        this.longDescription = longDescription;
    }

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="vaccineID")
    private Vaccine vaccine;

    public SideEffect(Long ID, Float frequency, String description, String longDescription) {
        this.ID = ID;
        this.frequency = frequency;
        this.description = description;
        this.longDescription = longDescription;
    }


    public Long getID() {
        return ID;
    }

    public SideEffect() {
    }

    @Column(name="description")
    String description;

    public Float getFrequency() {
        return frequency;
    }

    public void setFrequency(Float frequency) {
        this.frequency = frequency;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @Column(name="longDescription")
    String longDescription;


    public void setID(Long id) {
        this.ID = id;
    }


}
