package hr.tvz.zendeli.vaxapp;

import java.util.List;


public class VaccineJpaRepoImpl  {

   private final VaccineJpaRepository vaccineJpaRepository;

    public VaccineJpaRepoImpl(VaccineJpaRepository vaccineJpaRepository) {
        this.vaccineJpaRepository = vaccineJpaRepository;
    }
    List<Vaccine> findAllVaccines(){
        List<Vaccine> vaccines = vaccineJpaRepository.findAll();
        return vaccines;
    }

    List<Vaccine> findVaccineByResearchName(String researchName){
        List<Vaccine> vaccines = vaccineJpaRepository.findVaccineByResearchName(researchName);

        return vaccines;
    }

    void deleteByResearchName(String researchName){
        vaccineJpaRepository.deleteByResearchName(researchName);
        return;
    }

}
