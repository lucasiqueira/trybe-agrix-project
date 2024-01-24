package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * The type Crop dto.
 */
public record CropDto(Long id, String name, Double plantedArea, Long farmId, LocalDate plantedDate,
                      LocalDate harvestDate) {

  public Crop toEntity() {
    return new Crop(id, name, plantedArea, null, plantedDate, harvestDate, null);
  }

  public static CropDto fromEntity(Crop crop) {
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId(),
                       crop.getPlantedDate(), crop.getHarvestDate());
  }
}