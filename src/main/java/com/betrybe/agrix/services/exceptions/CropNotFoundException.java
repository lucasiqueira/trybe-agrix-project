package com.betrybe.agrix.services.exceptions;

/**
 * The type Crop not found exception.
 */
public class CropNotFoundException extends RuntimeException {

  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}