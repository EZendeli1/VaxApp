package hr.tvz.zendeli.vaxapp;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class VaccineRepositoryImplementation implements VaccineRepository {

    private final List<Vaccine> MOCKED_VACCINES = new ArrayList<>(Arrays.asList(

            new Vaccine("BNT162b2", "Comirnaty", "mRNA-based vaccine", 2, 13000000),
            new Vaccine("mRNA1273", "Moderna COVID‑19 Vaccine", "mRNA-based vaccine", 2, 50000000),
            new Vaccine("AZD1222", "COVID-19 Vaccine AstraZeneca", "Adenovirus vaccine", 2, 30000000),
            new Vaccine("SputnikV", "Sputnik V", "Recombinant adenovirus vaccine (rAd26 and rAd5)", 2, 10000000),
            new Vaccine("JNJ", "COVID-19 Vaccine Janssen ", "Non-replicating viral vector", 1, 100000000),
            new Vaccine("CoronaVac", "CoronaVac", "Inactivated vaccine (formalin with alum adjuvant)", 2, 802000000),
            new Vaccine("BBIBPCorV", "BBIBP-CorV", "Inactivated vaccine", 2, 100000000),
            new Vaccine("EpiVacCorona", "EpiVacCorona", "Peptide vaccine", 2, 20000000),
            new Vaccine("Covaxin", "Covaxin", "Inactivated vaccine", 2, 2000000)

    ));

    @Override
    public List<Vaccine> findall() {
        return MOCKED_VACCINES;
    }

    @Override
    public List<Vaccine> findByDose(boolean singleDose) {

        if (singleDose)
            return MOCKED_VACCINES.stream().filter(it -> Objects.equals(it.getRequiredDoses(), 1)).collect(Collectors.toList());

        return MOCKED_VACCINES.stream().filter(it -> it.getRequiredDoses() > 1).collect(Collectors.toList());
    }

    @Override
    public Optional<Vaccine> findVaccineByResearchName(String researchName) {
        return MOCKED_VACCINES.stream().filter(it -> Objects.equals(it.getResearchName(), researchName)).findAny();
    }

    @Override
    public Vaccine save(VaccineCommand vaccineCommand) {
        Vaccine newVaccine = new Vaccine(vaccineCommand.getResearchName(), vaccineCommand.getManufacturerName(), vaccineCommand.getType(), vaccineCommand.getNumberOfShots(), vaccineCommand.getAvailableDoses());
        MOCKED_VACCINES.add(newVaccine);

        return newVaccine;
    }

    @Override
    public Optional<Vaccine> update(String researchName,VaccineCommand vaccineCommand) {

      //  MOCKED_VACCINES.get(MOCKED_VACCINES.indexOf(MOCKED_VACCINES.stream().filter(it -> Objects.equals(it.getResearchName(),researchName)).findAny())).setNumberOfShots(vaccineCommand.getNumberOfShots());
        MOCKED_VACCINES.stream().filter(it -> Objects.equals(it.getResearchName(), researchName)).findAny().get().setStockOfDoses(vaccineCommand.getAvailableDoses());


        return MOCKED_VACCINES.stream().filter(it -> Objects.equals(it.getResearchName(), researchName)).findAny();
    }

    @Override
    public List<Vaccine> findVaccineByResearchNameWithWildCards(String researchName) {
        return null;
    }

    @Override
    public void deleteByResearchName(String researchName) {

        MOCKED_VACCINES.removeIf(vaccine -> vaccine.getResearchName().equals(researchName));

    }

    @Override
    public List<Vaccine> findVaccineByStockOfDosesGreaterThan(Integer stockOfDoses) {
        return null;
    }
}
