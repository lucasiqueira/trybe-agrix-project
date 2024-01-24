package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropCreationDto;
import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm farm = farmService.createFarm(farmDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(farm);
  }

  @GetMapping
  @Secured({"MANAGER", "ADMIN", "USER"})
  public ResponseEntity<List<Farm>> getAllFarms() {
    List<Farm> farms = farmService.getAllFarms();
    return ResponseEntity.ok(farms);
  }

  @GetMapping("/{farmId}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {
    Farm farm = farmService.getFarmById(farmId);
    return ResponseEntity.ok(farm);
  }

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> addNewCropToFarm(
      @PathVariable Long farmId,
      @RequestBody CropCreationDto cropCreationDto
  ) {
    Crop crop = cropService.createCrop(cropCreationDto.toEntity(), farmId);
    return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.fromEntity(crop));
  }

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropByFarm(@PathVariable Long farmId) {
    List<Crop> crops = cropService.getCropsByFarm(farmId);
    return ResponseEntity.ok(crops.stream().map(CropDto::fromEntity).toList());
  }
}
