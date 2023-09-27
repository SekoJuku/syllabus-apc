package kz.syllabus.util;

import kz.syllabus.properties.TokenProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class JWTUtils {

    private final JwtEncoder      jwtEncoder;
    private final TokenProperties tokenProperties;

    public String generateAccessToken(UserDetails user) {

        var now = Instant.now();

        var claims = JwtClaimsSet.builder()
                .subject(user.getUsername())
                .issuer(tokenProperties.issuer())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(tokenProperties.accessExpirationTime()))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(UserDetails user) {
        var now = Instant.now();

        var claims = JwtClaimsSet.builder()
                .subject(user.getUsername())
                .issuer(tokenProperties.issuer())
                .issuedAt(now)
                .claim("Type", "Refresh")
                .expiresAt(now.plusSeconds(tokenProperties.refreshExpirationTime()))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
