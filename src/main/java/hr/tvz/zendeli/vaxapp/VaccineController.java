package hr.tvz.zendeli.vaxapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("vaccine")
@CrossOrigin(origins = "http://localhost:4200")
public class VaccineController {

    private final VaccineService vaccineService;
    @Autowired
    private SideEffectJpaRepository sideEffectJpaRepository;
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }




    @GetMapping
    public List<VaccineDTO> getAllVaccines() {
        return vaccineService.findAll();
    }

    @GetMapping("/{researchName}")
    public ResponseEntity<VaccineDTO> getVaccineByResearchName(@PathVariable final String researchName) {
        return Optional.ofNullable(vaccineService.findVaccineByResearchName(researchName)).map(
                vaccineDTO -> ResponseEntity.status(HttpStatus.OK)
                        .body(vaccineDTO)

        )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NO_CONTENT)
                                .build()
                );
    }

    @GetMapping(params = "singleDose")
    public List<VaccineDTO> getVaccineByDose(@RequestParam final boolean singleDose) {

        return vaccineService.findByDose(singleDose);
    }
    @GetMapping(params = "contains")
    public List<VaccineDTO> getVaccineByResearchNameWithWildCards(@RequestParam final String contains) {

        return vaccineService.findVaccineByResearchNameWithWildCards(contains);
    }

    @Secured({"ROLE_ADMIN","ROLE_CREATOR"})
    @PostMapping
    public ResponseEntity<VaccineDTO> save(@Valid @RequestBody final VaccineCommand command) {
        return vaccineService.save(command).map(
                vaccineDTO -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(vaccineDTO)

        )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT)
                                .build()
                );


    }




    @Secured({"ROLE_ADMIN","ROLE_CREATOR"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{researchName}")
    public void delete(@PathVariable String researchName) {
        vaccineService.deleteByResearchName(researchName);

    }

    @Secured({"ROLE_ADMIN","ROLE_CREATOR"})
    @PutMapping("/{researchName}")
    public ResponseEntity<VaccineDTO> update(@PathVariable final String researchName,@Valid @RequestBody
    final VaccineCommand vaccineCommand) {
        return vaccineService.update(researchName,vaccineCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        ()-> ResponseEntity.notFound().build()
                );





    }






}
