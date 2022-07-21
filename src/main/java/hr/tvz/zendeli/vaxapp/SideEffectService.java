package hr.tvz.zendeli.vaxapp;

import java.util.List;

public interface SideEffectService {

    List<SideEffectDTO> findAllSideEffects();
    List<SideEffectDTO> findSideEffectsByVaccineResearchName(String researchName);
    List<SideEffectDTO> findSideEffectsByLongDescription(String longDescription);

}
