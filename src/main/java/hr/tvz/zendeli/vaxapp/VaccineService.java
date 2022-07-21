package hr.tvz.zendeli.vaxapp;

import java.util.List;
import java.util.Optional;

public interface VaccineService {

    List<VaccineDTO> findAll();
    List<VaccineDTO> findByDose(boolean singleDose);
    Optional<VaccineDTO> save (VaccineCommand vaccineCommand);
    VaccineDTO findVaccineByResearchName(String researchName);
    Optional<VaccineDTO> update (String researchName,VaccineCommand vaccineCommand);
    List<VaccineDTO>findVaccineByResearchNameWithWildCards(String researchName);
    void deleteByResearchName(String researchName);
    List<VaccineDTO> findByStockOfDosesGreaterThen(Integer stockOfDoses);

}
