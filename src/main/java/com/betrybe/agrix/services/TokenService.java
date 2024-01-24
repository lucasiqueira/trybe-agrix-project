package com.betrybe.agrix.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * The type Token service.
 */
@Service
public class TokenService {

  private Algorithm algorithm;

  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Generate token string.
   *
   * @param userDetails the user details
   * @return the string
   */
  public String generateToken(UserDetails userDetails) {
    return JWT.create()
        .withIssuer("Agrix")
        .withSubject(userDetails.getUsername())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }

  /**
   * Validate token string.
   *
   * @param token the token
   * @return the string
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
        .withIssuer("Agrix")
        .build()
        .verify(token)
        .getSubject();
  }
}
