package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.services.exceptions.CropNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmService farmService;

  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository    the crop repository
   * @param farmService       the farm service
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmService farmService,
      FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create crop crop.
   *
   * @param crop   the crop
   * @param farmId the farm id
   * @return the crop
   */
  public Crop createCrop(Crop crop, Long farmId) {
    Farm farm = farmService.getFarmById(farmId);
    crop.setFarm(farm);
    farm.getCrops().add(crop);
    return cropRepository.save(crop);
  }

  public List<Crop> getCropsByFarm(Long farmId) {
    Farm farm = farmService.getFarmById(farmId);
    return farm.getCrops();
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Crop getCropById(Long cropId) {
    return cropRepository.findById(cropId).orElseThrow(CropNotFoundException::new);
  }

  public List<Crop> getCropsByHarvestDateBetween(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Add fertilizer to crop crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop
   */
  public Crop addFertilizerToCrop(Long cropId, Long fertilizerId) {
    Crop crop = getCropById(cropId);
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);
    crop.getFertilizers().add(fertilizer);
    return cropRepository.save(crop);
  }

  public List<Fertilizer> getFertilizersByCrop(Long cropId) {
    Crop crop = getCropById(cropId);
    return crop.getFertilizers();
  }
}
