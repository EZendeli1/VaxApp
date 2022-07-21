package hr.tvz.zendeli.vaxapp;

import lombok.Data;

import java.util.List;

@Data
public class VaccineDTO {

    String researchName,manufacturerName, type;
    Integer numberOfShots, availableDoses;
    Long ID;
    List<SideEffectDTO> sideEffectDTOS;

    public List<SideEffectDTO> getSideEffectDTOS() {
        return sideEffectDTOS;
    }

    public VaccineDTO(String researchName, String manufacturerName, String type, Integer numberOfShots, Integer availableDoses, Long ID, List<SideEffectDTO> sideEffectDTOS) {
        this.researchName = researchName;
        this.manufacturerName= manufacturerName;
        this.type = type;
        this.numberOfShots = numberOfShots;
        this.availableDoses = availableDoses;
        this.ID = ID;
        this.sideEffectDTOS = sideEffectDTOS;
    }

    public void setSideEffectDTOS(List<SideEffectDTO> sideEffectDTOS) {
        this.sideEffectDTOS = sideEffectDTOS;
    }

    public String getType() {
        return type;
    }

    public Long getID() {
        return ID;
    }

    public VaccineDTO(String researchName, String manufacturerName, String type, Integer numberOfShots, Integer availableDoses, Long ID) {
        this.researchName = researchName;
        this.manufacturerName = manufacturerName;
        this.type = type;
        this.numberOfShots = numberOfShots;
        this.availableDoses = availableDoses;
        this.ID = ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public VaccineDTO(String researchName, String manufacturerName, String type, Integer numberOfShots, Integer availableDoses) {
        this.researchName = researchName;
        this.manufacturerName= manufacturerName;
        this.type = type;
        this.numberOfShots = numberOfShots;
        this.availableDoses = availableDoses;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAvailableDoses() {
        return availableDoses;
    }

    public void setAvailableDoses(Integer availableDoses) {
        this.availableDoses = availableDoses;
    }

    public String getResearchName() {
        return researchName;
    }

    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }



    public String getManufacturersName() {
        return manufacturerName;
    }

    public void setManufacturersName(String manufacturersName) {
        this.manufacturerName = manufacturersName;
    }

    public Integer getNumberOfShots() {
        return numberOfShots;
    }

    public void setNumberOfShots(Integer numberOfShots) {
        this.numberOfShots = numberOfShots;
    }

    public VaccineDTO(String manufacturerName, Integer numberOfShots) {
        this.manufacturerName = manufacturerName;
        this.numberOfShots = numberOfShots;
    }

 /*   public VaccineDTO(String researchName, String manufacturerName,, String type, Integer numberOfShots, Integer availableDoses) {
        this.researchName = researchName;
        this.manufacturerName, = manufacturerName,;
        this.type = type;
        this.numberOfShots = numberOfShots;
        this.availableDoses = availableDoses;
    }*/

    public VaccineDTO(String researchName, String manufacturerName, Integer numberOfShots) {
        this.researchName = researchName;
        this.manufacturerName= manufacturerName;
        this.numberOfShots = numberOfShots;
    }

    @Override
    public String toString() {
        return "VaccineDTO{ ID ="+ ID + " Manufacturers Name'=" + manufacturerName + '\'' +
                "Research Name'=" + researchName + '\'' +
                "Vaccine type'=" + type + '\'' +
                "Required Doses=" + numberOfShots +'\'' +
                "Stock Doses=" + availableDoses +
                '}';
    }
}
