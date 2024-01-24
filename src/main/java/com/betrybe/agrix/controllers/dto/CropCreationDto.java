package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * The type Crop creation dto.
 */
public record CropCreationDto(String name, Double plantedArea, LocalDate plantedDate,
                              LocalDate harvestDate) {

  public Crop toEntity() {
    return new Crop(null, name, plantedArea, null, plantedDate, harvestDate, null);
  }

}
