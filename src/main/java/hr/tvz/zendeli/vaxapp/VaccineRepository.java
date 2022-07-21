package hr.tvz.zendeli.vaxapp;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository {

    List<Vaccine> findall();

    List<Vaccine> findByDose(boolean singleDose);

    Optional<Vaccine> findVaccineByResearchName(String researchName);

    Vaccine save(VaccineCommand vaccineCommand);
    Optional<Vaccine> update(String researchName,VaccineCommand vaccineCommand);

    List<Vaccine> findVaccineByResearchNameWithWildCards(String researchName);

    void deleteByResearchName(String researchName);

    List<Vaccine> findVaccineByStockOfDosesGreaterThan(Integer stockOfDoses);


}
