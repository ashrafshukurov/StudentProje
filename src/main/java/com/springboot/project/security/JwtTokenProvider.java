package com.springboot.project.security;

import com.springboot.project.model.TokenType;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${security.jwtSecret}")
    private String jwtSecret;
    @Value("${security.accessTokenExpirationInMs}")
    private int accessTokenExpirationInMs;

    @Value("${security.refreshTokenExpirationInMs}")
    private int refreshTokenExpirationInMs;
    public String generateToken(UserPrincipal userPrincipal, TokenType tokenType) {
        var expirationInMs=tokenType== TokenType.ACCESS_TOKEN ? accessTokenExpirationInMs:refreshTokenExpirationInMs;
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() +
                expirationInMs);
        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String authToken) {//Jwt dedymz tokendir
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);//parser methodu JWT ni parse edir ve yoxlayor;

            //Exception handling mentiqi ucundur sehv key olsa SignatureException bunu atacaq Expire olunsa ExpiredJwtException bunu atacaq

        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}