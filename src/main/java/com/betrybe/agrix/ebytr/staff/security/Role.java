package com.betrybe.agrix.ebytr.staff.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum representing a Role.
 */
public enum Role implements GrantedAuthority {
  ADMIN("ADMIN"),
  MANAGER("MANAGER"),
  USER("USER");

  private final String name;

  Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getAuthority() {
    return this.getName();
  }
}