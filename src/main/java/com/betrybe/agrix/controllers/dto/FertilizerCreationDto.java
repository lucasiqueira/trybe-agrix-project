package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * The type Fertilizer creation dto.
 */
public record FertilizerCreationDto(String name, String brand, String composition) {

  /**
   * To entity fertilizer.
   *
   * @return the fertilizer
   */
  public Fertilizer toEntity() {
    return new Fertilizer(null, name, brand, composition, null);
  }
}
