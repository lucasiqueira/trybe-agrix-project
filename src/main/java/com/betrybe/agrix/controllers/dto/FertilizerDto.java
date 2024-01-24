package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * The type Fertilizer dto.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * To entity fertilizer.
   *
   * @return the fertilizer
   */
  public Fertilizer toEntity() {
    return new Fertilizer(id, name, brand, composition, null);
  }

  /**
   * From entity fertilizer dto.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer dto
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
      fertilizer.getId(),
      fertilizer.getName(),
      fertilizer.getBrand(),
      fertilizer.getComposition()
    );
  }
}
