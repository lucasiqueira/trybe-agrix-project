package com.betrybe.agrix.services.exceptions;

/**
 * The type Farm not found exception.
 */
public class FarmNotFoundException extends RuntimeException {

  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }

}