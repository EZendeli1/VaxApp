package hr.tvz.zendeli.vaxapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SideEffectJpaRepository extends JpaRepository<SideEffect,Long> {

        List<SideEffect>findAll();
        List<SideEffect> findSideEffectsByVaccineResearchName(String researchName);
        List<SideEffect> findBylongDescriptionContainingIgnoreCase(String longDescription);




}
