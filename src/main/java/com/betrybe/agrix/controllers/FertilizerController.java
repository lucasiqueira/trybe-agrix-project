package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerCreationDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
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
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  public ResponseEntity<Fertilizer> createFertilizer(
      @RequestBody FertilizerCreationDto fertilizerCreationDto) {
    Fertilizer fertilizer = fertilizerService.createFertilizer(fertilizerCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizer);
  }

  @GetMapping
  @Secured("ADMIN")
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();
    return ResponseEntity.ok(fertilizers.stream().map(FertilizerDto::fromEntity).toList());
  }

  @GetMapping("/{fertilizerId}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long fertilizerId) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);
    return ResponseEntity.ok(FertilizerDto.fromEntity(fertilizer));
  }
}
