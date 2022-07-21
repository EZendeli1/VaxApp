package hr.tvz.zendeli.vaxapp;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SideEffectJpaImpl{
   private final SideEffectJpaRepository sideEffectJpaRepository;

    public SideEffectJpaImpl(SideEffectJpaRepository sideEffectJpaRepository) {
        this.sideEffectJpaRepository = sideEffectJpaRepository;
    }

    List<SideEffect> findAllSideEffects() {

       List<SideEffect> sideEffects= sideEffectJpaRepository.findAll();
       return sideEffects;
    }

    List<SideEffect> findSideEffectsByVaccineResearchName(String researchName){
       List<SideEffect> sideEffectsbyRN=sideEffectJpaRepository.findSideEffectsByVaccineResearchName(researchName);
       return sideEffectsbyRN;

    }
    List<SideEffect> findBylongDescriptionContaining(String longDescription){
        List<SideEffect> sideEffectsbyLD=sideEffectJpaRepository.findBylongDescriptionContainingIgnoreCase(longDescription);
        return sideEffectsbyLD;
    }

}
