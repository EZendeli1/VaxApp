package hr.tvz.zendeli.vaxapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class VaccineServiceImplementationTest {

    @Autowired
    VaccineService vaccineService;

    @MockBean
    SideEffectJpaRepository sideEffectJpaRepository;

    @MockBean
    JdbcVaccineRepository jdbcVaccineRepository;



    @Test
    @DirtiesContext()
    void findAll() {
        reset(jdbcVaccineRepository);

        List<Vaccine> vaccinesWhen = new ArrayList() {{
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));

        }};
        Mockito.when(jdbcVaccineRepository.findall()).thenReturn(vaccinesWhen);


        List<Vaccine> vaccines= jdbcVaccineRepository.findall();
        Assertions.assertNotNull(vaccines);
        Assertions.assertEquals(vaccines.size(),9);




    }

    @Test
    @DirtiesContext()
    void findByDose() {
        List<Vaccine> vaccinesWhen = new ArrayList() {{
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));
        }};
        when(jdbcVaccineRepository.findByDose(true)).thenReturn(
                vaccinesWhen
        );



        List<Vaccine> vaccines = jdbcVaccineRepository.findByDose(true);
        Assertions.assertNotNull(vaccines);
        Assertions.assertEquals(vaccines.get(0).getResearchName(), "TestVaccine");
        Assertions.assertEquals(vaccines.get(0).getManufacturersName(), "TestVaccine");

    }

    @Test
    @Transactional
    @DirtiesContext()
    void save() {
        VaccineCommand vaccineCommand= new VaccineCommand("TestVaccine", "TestVaccine", "TestVaccine",1,1);

        jdbcVaccineRepository.save(vaccineCommand);
        verify(jdbcVaccineRepository).save(vaccineCommand);

    }

    @Test
    @DirtiesContext()
    void findVaccineByResearchName() {

        when(jdbcVaccineRepository.findVaccineByResearchName("TestVaccine")).thenReturn(
               Optional.of(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1))
        );




       Vaccine vaccines = jdbcVaccineRepository.findVaccineByResearchName("TestVaccine").orElse(null);
        Assertions.assertNotNull(vaccines);
        Assertions.assertEquals(vaccines.getResearchName(), "TestVaccine");
        Assertions.assertEquals(vaccines.getManufacturersName(), "TestVaccine");
    }

    @Test
    @Transactional
    @DirtiesContext()
    void update() {

        VaccineCommand vaccineCommand= new VaccineCommand("TestVaccine", "TestVaccine", "TestVaccine",1,1);

        jdbcVaccineRepository.update("TestVaccine",vaccineCommand);
        verify(jdbcVaccineRepository).update("TestVaccine",vaccineCommand);
    }

    @Test
    @Transactional
    @DirtiesContext()
    void findVaccineByResearchNameWithWildCards() {

        List<Vaccine> vaccinesWhen = new ArrayList() {{
            add(new Vaccine("TestVaccine", "TestVaccine", "TestVaccine",1,1));
        }};
        when(jdbcVaccineRepository.findVaccineByResearchNameWithWildCards("TestVaccine")).thenReturn(
                vaccinesWhen
        );


        List<Vaccine> vaccines = jdbcVaccineRepository.findVaccineByResearchNameWithWildCards("TestVaccine");
        Assertions.assertNotNull(vaccines);
        Assertions.assertEquals(vaccines.get(0).getResearchName(), "TestVaccine");
        Assertions.assertEquals(vaccines.get(0).getManufacturersName(), "TestVaccine");




    }

    @Test
    @Transactional
    @DirtiesContext()
    void deleteByResearchName() {
        jdbcVaccineRepository.deleteByResearchName("TestVaccine");
        verify(jdbcVaccineRepository).deleteByResearchName("TestVaccine");



    }
}