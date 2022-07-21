package hr.tvz.zendeli.vaxapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcVaccineRepository implements VaccineRepository {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert vaccineInserter;
    @Autowired
    private final SideEffectJpaRepository sideEffectJpaRepository;
    @Autowired
    private final VaccineJpaRepository vaccineJpaRepository;

    public JdbcVaccineRepository(JdbcTemplate jdbc, SideEffectJpaRepository sideEffectJpaRepository, VaccineJpaRepository vaccineJpaRepository) {
        this.jdbc = jdbc;
        this.vaccineInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("VACCINES")
                .usingGeneratedKeyColumns("ID");
        this.sideEffectJpaRepository = sideEffectJpaRepository;
        this.vaccineJpaRepository = vaccineJpaRepository;
    }

    @Override
    public List<Vaccine> findall() {
//  System.out.print("test " + jdbc.query("select ID, researchName, manufacturersName,type,numberOfShots,availableDoses FROM VACCINES", this::mapRowToVaccine).get(0).getNumberOfShots());

        //return jdbc.query("select ID, researchName, manufacturersName,type,numberOfShots,availableDoses FROM VACCINES", this::mapRowToVaccine);


        List<Vaccine>  vaccines= vaccineJpaRepository.findAll();



        return vaccines;

    }

    @Override
    public List<Vaccine> findByDose(boolean singleDose) {
        List<Vaccine> vaccinesByDose = new ArrayList<>();
        if (singleDose) {
            return vaccinesByDose = vaccineJpaRepository.findVaccineByRequiredDoses(1);//jdbc.query("select ID, researchName, manufacturersName,vaccineType,requiredDoses,stockOfDoses FROM VACCINES where requiredDoses LIKE 1", this::mapRowToVaccine);
        }
        return vaccinesByDose = vaccineJpaRepository.findVaccineByRequiredDoses(2);//vaccinesByDose = jdbc.query("select ID, researchName, manufacturersName,vaccineType,requiredDoses,stockOfDoses FROM VACCINES where requiredDoses >1", this::mapRowToVaccine);

    }

    @Override
    public Optional<Vaccine> findVaccineByResearchName(String researchName) {
        List<Vaccine> vaccinesByRN = new ArrayList<>();

    /*    vaccinesByRN = jdbc.query("select ID, researchName, manufacturersName,vaccineType,requiredDoses,stockOfDoses FROM VACCINES where researchName LIKE ?", this::mapRowToVaccine, researchName);
        if (vaccinesByRN.size() == 0) {
            return Optional.empty();
        }*/

        List<SideEffect> sideEffects=sideEffectJpaRepository.findAll();


        List<Vaccine> Vaccinejpa = vaccineJpaRepository.findVaccineByResearchName(researchName);


        if (Vaccinejpa.size() == 0) {
            return Optional.empty();
        }

        return Optional.ofNullable(Vaccinejpa.get(0));
        //return Optional.of(jdbc.query("select ID, researchName, manufacturersName,type,numberOfShots,availableDoses FROM VACCINES where researchName LIKE ?", this::mapRowToVaccine, researchName));

// return Optional.empty();

    }

    public List<Vaccine> findVaccineByResearchNameWithWildCards(String researchName) {
        List<Vaccine> vaccinesByRN = new ArrayList<>();
       // researchName=researchName+"%";
        vaccinesByRN = vaccineJpaRepository.findVaccineByResearchNameContainingIgnoreCase(researchName);//jdbc.query("select ID, researchName, manufacturersName,vaccineType,requiredDoses,stockOfDoses FROM VACCINES where UPPER(researchName) LIKE UPPER(?)",this::mapRowToVaccine,researchName);


        return vaccinesByRN;
    }



    @Transactional
    @Override
    public Vaccine save(VaccineCommand vaccineCommand) {


        Vaccine newVaccine = new Vaccine(vaccineCommand.getResearchName(), vaccineCommand.getManufacturerName(), vaccineCommand.getType(), vaccineCommand.getNumberOfShots(), vaccineCommand.getAvailableDoses());
       /* Map<String, Object> values = new HashMap<>();
        values.put("researchName", vaccineCommand.getResearchName());
        values.put("manufacturersName", vaccineCommand.getManufacturerName());
        values.put("vaccineType", vaccineCommand.getType());
        values.put("requiredDoses", vaccineCommand.getNumberOfShots());
        values.put("stockOfDoses", vaccineCommand.getAvailableDoses());
        // values.put("ID",8);
        long key = vaccineInserter.executeAndReturnKey(values).longValue();*/


        vaccineJpaRepository.saveVaccine(vaccineCommand.getResearchName(),vaccineCommand.getManufacturerName(),vaccineCommand.getType(),vaccineCommand.getNumberOfShots(),vaccineCommand.getAvailableDoses());
        return newVaccine;
    }
    @Transactional
    @Override
    public Optional<Vaccine> update(String researchName, VaccineCommand vaccineCommand) {

        Vaccine newVaccine = new Vaccine(vaccineCommand.getResearchName(), vaccineCommand.getManufacturerName(), vaccineCommand.getType(), vaccineCommand.getNumberOfShots(), vaccineCommand.getAvailableDoses());

      //  int key = jdbc.update("UPDATE VACCINES SET stockOfDoses=" + newVaccine.stockOfDoses + " WHERE researchName =? ;", researchName);
        //Optional<Vaccine> updatedVaccine =findVaccineByResearchName(newVaccine.researchName);
        vaccineJpaRepository.updateStockOfDosesByResearchName(vaccineCommand.getAvailableDoses(),researchName);
        return Optional.of(newVaccine);
    }
    @Transactional
    @Override
    public void deleteByResearchName(String researchName) {
     /*   String sqlDelete = "DELETE FROM VACCINES WHERE researchName = ?";
        jdbc.update(sqlDelete, researchName);
        */

        vaccineJpaRepository.deleteByResearchName(researchName);
    }

    @Override
    public List<Vaccine> findVaccineByStockOfDosesGreaterThan(Integer stockOfDoses) {
        List<Vaccine> vaccines = vaccineJpaRepository.findVaccineByStockOfDosesGreaterThan(stockOfDoses);
        return vaccines;
    }

    private Vaccine mapRowToVaccine(ResultSet rs, int rowNum) throws SQLException {
        Vaccine vaccine = new Vaccine(
                rs.getString("researchName"),
                rs.getString("manufacturersName"),
                rs.getString("type"),
                rs.getInt("numberOfShots"),
                rs.getInt("availableDoses"),
                rs.getLong(1));


        return vaccine;
    }

    private SideEffect mapRowToSideEffect(ResultSet rs, int rowNum) throws SQLException {
        SideEffect sideEffect = new SideEffect(
                rs.getLong("ID"),
                rs.getFloat("frequency"),
                rs.getInt("vaccineID"),
                rs.getString("description"),
                rs.getString("longDescription"));



        return sideEffect;
    }


}
