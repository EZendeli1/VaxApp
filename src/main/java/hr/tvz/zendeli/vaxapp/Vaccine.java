package hr.tvz.zendeli.vaxapp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "VACCINES")
public class Vaccine implements Serializable {
    @Column(name = "researchName")
    String researchName;
    @Column(name = "manufacturerName")
    String manufacturersName;
    @Column(name = "type")
    String vaccineType;
    @Column(name = "numberOfShots")
    Integer requiredDoses;
    @Column(name = "availableDoses")
    Integer   stockOfDoses;
    @Id
    @Column(name="ID")
    @GeneratedValue
    Long ID;

    @OneToMany(mappedBy = "vaccine",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<SideEffect> sideEffects;


    public List<SideEffect> getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(List<SideEffect> sideEffects) {
        this.sideEffects = sideEffects;
    }

    public Vaccine(String researchName, String manufacturersName, String vaccineType, Integer requiredDoses, Integer stockOfDoses) {
        this.researchName = researchName;
        this.manufacturersName = manufacturersName;
        this.vaccineType = vaccineType;
        this.requiredDoses = requiredDoses;
        this.stockOfDoses = stockOfDoses;
    }

    public Long getID() {
        return ID;
    }

    public Vaccine(String researchName, String manufacturersName, String vaccineType, Integer requiredDoses, Integer stockOfDoses, Long ID) {
        this.researchName = researchName;
        this.manufacturersName = manufacturersName;
        this.vaccineType = vaccineType;
        this.requiredDoses = requiredDoses;
        this.stockOfDoses = stockOfDoses;
        this.ID = ID;
    }

    public Vaccine() {
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getResearchName() {
        return researchName;
    }

    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }

    public String getManufacturersName() {
        return manufacturersName;
    }

    public void setManufacturersName(String manufacturersName) {
        this.manufacturersName = manufacturersName;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public Integer getRequiredDoses() {
        return requiredDoses;
    }

    public void setRequiredDoses(Integer requiredDoses) {
        this.requiredDoses = requiredDoses;
    }

    public Integer getStockOfDoses() {
        return stockOfDoses;
    }

    public void setStockOfDoses(Integer stockOfDoses) {
        this.stockOfDoses = stockOfDoses;
    }
}
