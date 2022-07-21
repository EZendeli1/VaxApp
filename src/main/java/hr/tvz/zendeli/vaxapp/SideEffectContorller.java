package hr.tvz.zendeli.vaxapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("side-effect")
@CrossOrigin(origins = "http://localhost:4200")
public class SideEffectContorller {

    private final SideEffectService sideEffectService;
    public SideEffectContorller(SideEffectService sideEffectService) {
        this.sideEffectService=sideEffectService;
    }


    @GetMapping
    public List<SideEffectDTO> getAllSideEffects() {
        return sideEffectService.findAllSideEffects();
    }






    @GetMapping("/{vaccineResearchName}")
    public ResponseEntity<List<SideEffectDTO>> getSideEffectByVaccineResearchName(@PathVariable final String vaccineResearchName) {
        return Optional.ofNullable(sideEffectService.findSideEffectsByVaccineResearchName(vaccineResearchName)).map(
                sideEffectDTOS -> ResponseEntity.status(HttpStatus.OK)
                        .body(sideEffectDTOS)

        )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NO_CONTENT)
                                .build()
                );
    }
    @GetMapping(params = "longDescription")
    public List<SideEffectDTO> getSideEffectsByLongDescription(@RequestParam final String longDescription) {

        return sideEffectService.findSideEffectsByLongDescription(longDescription);
    }




}
