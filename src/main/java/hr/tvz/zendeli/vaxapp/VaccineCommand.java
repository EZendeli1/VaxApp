package hr.tvz.zendeli.vaxapp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class VaccineCommand {

    @NotBlank(message = "Research name must not be empty")
    private String researchName;

    @NotBlank(message = "Manufacturers name must not be empty")
    private String manufacturerName;

    @NotBlank(message = "Vaccine type must not be empty")
    private String type;

    @NotNull(message = "Number of required doses must be entered")
    @PositiveOrZero(message = "Number of required doses must be entered as a positive integer")
    private Integer numberOfShots;

    @NotNull(message = "Stock of doses must be entered")
    @PositiveOrZero(message = "Stock doses must be entered as a positive integer")
    private Integer availableDoses;

    public VaccineCommand(@NotBlank(message = "Research name must not be empty") String researchName, @NotBlank(message = "Manufacturers name must not be empty") String manufacturerName, @NotBlank(message = "Vaccine type must not be empty") String type, @NotNull(message = "Number of required doses must be entered") @PositiveOrZero(message = "Number of required doses must be entered as a positive integer") Integer numberOfShots, @NotNull(message = "Stock of doses must be entered") @PositiveOrZero(message = "Stock doses must be entered as a positive integer") Integer availableDoses) {
        this.researchName = researchName;
        this.manufacturerName = manufacturerName;
        this.type = type;
        this.numberOfShots = numberOfShots;
        this.availableDoses = availableDoses;
    }

    public String getResearchName() {
        return researchName;
    }

    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberOfShots() {
        return numberOfShots;
    }

    public void setNumberOfShots(Integer numberOfShots) {
        this.numberOfShots = numberOfShots;
    }

    public Integer getAvailableDoses() {
        return availableDoses;
    }

    public void setAvailableDoses(Integer availableDoses) {
        this.availableDoses = availableDoses;
    }



}
