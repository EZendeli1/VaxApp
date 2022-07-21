package hr.tvz.zendeli.vaxapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SideEffectServiceImpl implements SideEffectService{
    private final SideEffectJpaImpl sideEffectJpa;
    public SideEffectServiceImpl(SideEffectJpaImpl sideEffectJpa) {
        this.sideEffectJpa=sideEffectJpa;
    }

    @Override
    public List<SideEffectDTO> findAllSideEffects() {


        List<SideEffectDTO> sideEffectDTOS= sideEffectJpa.findAllSideEffects().stream().map(this::mapSideEffectsToDTO).collect(Collectors.toList());
            return sideEffectDTOS;
    }

    @Override
    public List<SideEffectDTO> findSideEffectsByVaccineResearchName(String researchName) {
        List<SideEffectDTO> sideEffectDTOS=sideEffectJpa.findSideEffectsByVaccineResearchName(researchName).stream().map(this::mapSideEffectsToDTO).collect(Collectors.toList());
        return sideEffectDTOS;
    }

    @Override
    public List<SideEffectDTO> findSideEffectsByLongDescription(String longDescription) {
        List<SideEffectDTO> sideEffectDTOS=sideEffectJpa.findBylongDescriptionContaining(longDescription).stream().map(this::mapSideEffectsToDTO).collect(Collectors.toList());
        return sideEffectDTOS;
    }


    public SideEffectDTO mapSideEffectsToDTO(SideEffect sideEffect){

/*
        SideEffectDTO sideEffectDTO=new SideEffectDTO(sideEffect.ID,sideEffect.description,sideEffect.longDescription,sideEffect.frequency);
*/
        SideEffectDTO sideEffectDTO=new SideEffectDTO(sideEffect.description,sideEffect.longDescription,sideEffect.frequency);

        return sideEffectDTO;
    }

}
