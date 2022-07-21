package hr.tvz.zendeli.vaxapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class SideEffectServiceImplTest {


    @Autowired
    SideEffectService sideEffectService;


    @MockBean
    SideEffectJpaImpl sideEffectJpa;


    @MockBean
    JdbcVaccineRepository jdbcVaccineRepository;




    @Test
    void findSideEffectsByVaccineResearchName() {
        List<SideEffect> sideEffectsWhen = new ArrayList() {{
            add(new SideEffect(1L, 1F, "testSideEffectShort", "testSideEffectShortLong"));
        }};


        when(sideEffectJpa.findSideEffectsByVaccineResearchName("testReaserchName")).thenReturn(
                sideEffectsWhen
        );

        List<SideEffect> sideEffects = sideEffectJpa.findSideEffectsByVaccineResearchName("testReaserchName");
        Assertions.assertNotNull(sideEffects);
        Assertions.assertEquals(sideEffects.get(0).getDescription(), "testSideEffectShort");
        Assertions.assertEquals(sideEffects.get(0).getLongDescription(), "testSideEffectShortLong");
    }

    @Test
    void findSideEffectsByLongDescription() {
        List<SideEffect> sideEffectsWhen = new ArrayList() {{
            add(new SideEffect(1L, 1F, "testSideEffectShort", "testSideEffectShortLong"));
        }};

        when(sideEffectJpa.findBylongDescriptionContaining("testLongDesc")).thenReturn(
                sideEffectsWhen
        );

        List<SideEffect> sideEffects = sideEffectJpa.findBylongDescriptionContaining("testLongDesc");
        Assertions.assertNotNull(sideEffects);
        Assertions.assertEquals(sideEffects.get(0).getDescription(), "testSideEffectShort");
        Assertions.assertEquals(sideEffects.get(0).getLongDescription(), "testSideEffectShortLong");

    }


}