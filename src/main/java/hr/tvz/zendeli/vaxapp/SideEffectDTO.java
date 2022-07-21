package hr.tvz.zendeli.vaxapp;

import lombok.Data;

@Data
public class SideEffectDTO {



    /*     Long ID;
        Integer vaccineID;*/
    String shortDescription;
   String description;
    Float frequency;

    public SideEffectDTO(String shortDescription, String description, Float frequency) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.frequency = frequency;
    }

/*    public SideEffectDTO(Long ID, String description, String longDescription, Float frequency) {
        this.ID = ID;
        this.description = description;
        this.longDescription = longDescription;
        this.frequency = frequency;
    }*/
}

