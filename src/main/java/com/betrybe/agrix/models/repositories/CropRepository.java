package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  @Query("SELECT c FROM Crop c WHERE c.harvestDate BETWEEN ?1 AND ?2")
  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}