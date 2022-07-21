package hr.tvz.zendeli.vaxapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;
@SpringBootTest
class JdbcVaccineRepositoryTest {

    @Autowired
    VaccineJpaRepository vaccineJpaRepository;



    @Test
    void findall() {

        List<Vaccine> vaccines = vaccineJpaRepository.findAll();
        Assertions.assertNotNull(vaccines);
        Assertions.assertEquals(vaccines.size(),9);

    }

    @Test
    void findByDose() {
        List<Vaccine> vaccines = vaccineJpaRepository.findVaccineByRequiredDoses(1);
        Assertions.assertNotNull(vaccines);
        Assertions.assertEquals(vaccines.size(),1);
    }

    @Test
    void findVaccineByResearchName() {
        List<Vaccine> vaccines = vaccineJpaRepository.findVaccineByResearchName("BNT162b2");
        Assertions.assertNotNull(vaccines);
        Assertions.assertEquals(vaccines.size(),1);
    }

    @Test
    void findVaccineByResearchNameWithWildCards() {
        List<Vaccine> vaccines = vaccineJpaRepository.findVaccineByResearchNameContainingIgnoreCase("JnJ");
        Assertions.assertNotNull(vaccines);
        Assertions.assertEquals(vaccines.size(),1);
    }

    @Transactional
    @DirtiesContext
    @Test
    void save() {
        vaccineJpaRepository.saveVaccine("TestVaacine", "Comirnaty", "mRNA-based vaccine",2,13000000);
        List<Vaccine> vaccines = vaccineJpaRepository.findVaccineByResearchName("TestVaacine");
        Assertions.assertNotNull(vaccines);
        Assertions.assertEquals(vaccines.size(),1);
        Assertions.assertEquals(vaccines.get(0).getResearchName(),"TestVaacine");

    }

    @Transactional
    @DirtiesContext
    @Test
    void update() {
        vaccineJpaRepository.updateStockOfDosesByResearchName(17,"BNT162b2");
        List<Vaccine> vaccines = vaccineJpaRepository.findVaccineByResearchName("BNT162b2");
        Assertions.assertNotNull(vaccines);
        Assertions.assertTrue(vaccines.get(0).stockOfDoses.equals(17));
    }

    @Transactional
    @DirtiesContext
    @Test
    void deleteByResearchName() {
       vaccineJpaRepository.deleteByResearchName("BNT162b2");
        List<Vaccine> vaccines = vaccineJpaRepository.findVaccineByResearchName("BNT162b2");
        Assertions.assertNotNull(vaccines);
        Assertions.assertTrue(vaccines.isEmpty());


    }
}