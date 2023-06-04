package kz.syllabus.security;

import io.jsonwebtoken.*;

import kz.syllabus.config.properties.JwtProperties;
import kz.syllabus.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Log
@Component
@AllArgsConstructor
@NoArgsConstructor
public class JWTProvider {
    private JwtProperties jwtProperties;

    public String generateToken(User user) {
        Date date =
                Date.from(
                        LocalDate.now()
                                .plusDays(1)
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant());
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.severe("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.severe("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.severe("Malformed jwt");
        } catch (SignatureException sEx) {
            log.severe("Invalid signature");
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims =
                Jwts.parser()
                        .setSigningKey(jwtProperties.getSecret())
                        .parseClaimsJws(token)
                        .getBody();
        return claims.getSubject();
    }
}
