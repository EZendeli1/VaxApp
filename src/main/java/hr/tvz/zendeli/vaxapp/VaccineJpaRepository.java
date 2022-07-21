package hr.tvz.zendeli.vaxapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface VaccineJpaRepository extends JpaRepository<Vaccine,Long> {

    @Override
    List<Vaccine> findAll();
    List<Vaccine> findVaccineByResearchName(String researchName);
    void deleteByResearchName(String researchName);
    List<Vaccine> findVaccineByRequiredDoses(Integer requiredDoses);

    List<Vaccine> findVaccineByResearchNameContainingIgnoreCase(String researchName);
    List<Vaccine> findVaccineByStockOfDosesGreaterThan(Integer stockOfDoses);

    @Modifying
    @Query("UPDATE Vaccine v SET v.stockOfDoses=:stockOfDoses WHERE v.researchName =:researchName")
     void updateStockOfDosesByResearchName(@Param("stockOfDoses") Integer stockOfDoses, @Param("researchName") String researchName);


    @Modifying
    @Query(value = "INSERT INTO VACCINES (researchName,manufacturerName,type,numberOfShots,availableDoses) VALUES (:researchName,:manufacturerName,:type,:numberOfShots,:availableDoses)",nativeQuery = true)
    @Transactional
     void saveVaccine(@Param("researchName")String researchName, @Param("manufacturerName")String manufacturerName, @Param("type")String type
            , @Param("numberOfShots")Integer numberOfShots
            , @Param("availableDoses")Integer availableDoses);

}
