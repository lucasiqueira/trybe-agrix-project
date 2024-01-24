package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * The type Person dto.
 */
public record PersonDto(Long id, String username, Role role) {

  public static PersonDto fromEntity(Long id, String username, Role role) {
    return new PersonDto(id, username, role);
  }
}
