package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  private final FertilizerService fertilizerService;

  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  @GetMapping
  @Secured({"MANAGER", "ADMIN"})
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> crops = cropService.getAllCrops();
    return ResponseEntity.ok(crops.stream().map(CropDto::fromEntity).toList());
  }

  @GetMapping("/{cropId}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long cropId) {
    Crop crop = cropService.getCropById(cropId);
    return ResponseEntity.ok(CropDto.fromEntity(crop));
  }

  @GetMapping("search")
  public ResponseEntity<List<CropDto>> getCropsByHarvestDate(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end
  ) {
    List<Crop> crops = cropService.getCropsByHarvestDateBetween(start, end);
    return ResponseEntity.ok(crops.stream().map(CropDto::fromEntity).toList());
  }

  /**
   * Add fertilizer to crop response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> addFertilizerToCrop(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) {
    Crop crop = cropService.addFertilizerToCrop(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizersByCrop(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = cropService.getFertilizersByCrop(cropId);
    return ResponseEntity.ok(fertilizers.stream().map(FertilizerDto::fromEntity).toList());
  }
}
