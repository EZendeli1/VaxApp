package hr.tvz.zendeli.vaxapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SideEffectJpaImplTest {

    @Autowired
    SideEffectJpaRepository sideEffectJpaRepository;


    @Test
    void findAllSideEffects() {
        List<SideEffect> sideEffects = sideEffectJpaRepository.findAll();
        Assertions.assertNotNull(sideEffects);
        Assertions.assertEquals(sideEffects.size(),9);

    }

    @Test
    void findSideEffectsByVaccineResearchName() {

        List<SideEffect> sideEffects = sideEffectJpaRepository.findSideEffectsByVaccineResearchName("BNT162b2");

        Assertions.assertNotNull(sideEffects);
        Assertions.assertEquals(sideEffects.size(),1);
    }

    @Test
    void findBylongDescriptionContaining() {

        List<SideEffect> sideEffects = sideEffectJpaRepository.findBylongDescriptionContainingIgnoreCase("Crvenilo");
        Assertions.assertNotNull(sideEffects);

    }
}