package hr.tvz.zendeli.vaxapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImplementation implements VaccineService {

    private final VaccineRepository vaccineRepository;
    private final JdbcVaccineRepository jdbcVaccineRepository;
    private final SideEffectServiceImpl sideEffectService;
    public VaccineServiceImplementation(VaccineRepository vaccineRepository, JdbcVaccineRepository jdbcVaccineRepository, SideEffectServiceImpl sideEffectService) {
        this.vaccineRepository = vaccineRepository;
        this.jdbcVaccineRepository=jdbcVaccineRepository;
        this.sideEffectService = sideEffectService;
    }

    @Override
    public List<VaccineDTO> findAll() {
      /*  return jdbcVaccineRepository.findall().stream().map(this::mapVaccineToDTO).collect(Collectors.toList());*/

        List<VaccineDTO> vaccineDTOS;
      return vaccineDTOS= jdbcVaccineRepository.findall().stream().map(this::mapVaccineToDTO).collect(Collectors.toList());

    }

    @Override
    public List<VaccineDTO> findByDose(boolean singleDose) {
        return jdbcVaccineRepository.findByDose(singleDose).stream().map(this::mapVaccineToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<VaccineDTO> save(VaccineCommand vaccineCommand) {

        if (findVaccineByResearchName(vaccineCommand.getResearchName()) != null) {

            //return null;
            return Optional.empty();

        }

        Vaccine vaccine = jdbcVaccineRepository.save(vaccineCommand);
        VaccineDTO vaccineDTO = mapVaccineToDTO(vaccine);

        return Optional.of(vaccineDTO);

    }

    @Override
    public VaccineDTO findVaccineByResearchName(String researchName) {
        return jdbcVaccineRepository.findVaccineByResearchName(researchName).map(this::mapVaccineToDTO).orElse(null);
    }

    @Override
    public Optional<VaccineDTO> update(String researchName,VaccineCommand vaccineCommand) {

        if (findVaccineByResearchName(vaccineCommand.getResearchName()) == null) {

            //return null;
            return Optional.empty();

        }



        return Optional.of(jdbcVaccineRepository.update(researchName,vaccineCommand).map(this::mapVaccineToDTO).orElse(null));
    }

    @Override
    public List<VaccineDTO> findVaccineByResearchNameWithWildCards(String researchName) {
        return jdbcVaccineRepository.findVaccineByResearchNameWithWildCards(researchName).stream().map(this::mapVaccineToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteByResearchName(String researchName) {
       jdbcVaccineRepository.deleteByResearchName(researchName);


    }

    @Override
    public List<VaccineDTO> findByStockOfDosesGreaterThen(Integer stockOfDoses) {
        return jdbcVaccineRepository.findVaccineByStockOfDosesGreaterThan(stockOfDoses).stream().map(this::mapVaccineToDTO).collect(Collectors.toList());

    }

    private VaccineDTO mapVaccineToDTO(final Vaccine vaccine) {
        //return new VaccineDTO(vaccine.getManufacturerName(), vaccine.getNumberOfShots());
     //   return new VaccineDTO(vaccine.researchName,vaccine.manufacturersName,vaccine.type,vaccine.numberOfShots,vaccine.availableDoses,vaccine.ID);
        List<SideEffectDTO> sideEffect= sideEffectService.findSideEffectsByVaccineResearchName(vaccine.getResearchName());
        VaccineDTO vaccineDTO;
        vaccineDTO =new VaccineDTO(vaccine.getResearchName(),vaccine.getManufacturersName(),vaccine.getVaccineType(),vaccine.getRequiredDoses(),vaccine.getStockOfDoses(),vaccine.getID(),sideEffect);
        return  vaccineDTO;//new VaccineDTO(vaccine.getResearchName(),vaccine.getManufacturersName(),vaccine.getVaccineType(),vaccine.getRequiredDoses(),vaccine.getStockOfDoses(),vaccine.getID(),sideEffect);
    }


}
