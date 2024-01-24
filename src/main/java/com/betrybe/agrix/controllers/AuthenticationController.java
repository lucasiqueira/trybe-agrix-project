package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.AuthenticationDto;
import com.betrybe.agrix.controllers.dto.TokenDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Authentication controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  /**
   * Instantiates a new Authentication controller.
   *
   * @param authenticationManager the authentication manager
   * @param tokenService          the token service
   */
  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Login response entity.
   *
   * @param authDto the auth dto
   * @return the response entity
   */
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody AuthenticationDto authDto) {
    UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(
        authDto.username(), authDto.password());
    Authentication authentication = authenticationManager.authenticate(userNamePassword);

    Person person = (Person) authentication.getPrincipal();

    String token = tokenService.generateToken(person);

    TokenDto tokenDto = new TokenDto(token);

    return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
  }
}
