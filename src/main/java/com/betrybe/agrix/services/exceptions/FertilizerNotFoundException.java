package com.betrybe.agrix.services.exceptions;

/**
 * The type Fertilizer not found exception.
 */
public class FertilizerNotFoundException extends RuntimeException {

  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
