package kz.syllabus.common.service.impl;

import kz.syllabus.common.dto.request.TokenDtoRequest;
import kz.syllabus.common.dto.request.user.LoginDtoRequest;
import kz.syllabus.common.dto.response.TokenDtoResponse;
import kz.syllabus.common.properties.TokenProperties;
import kz.syllabus.common.service.AuthService;
import kz.syllabus.common.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;
    private final JwtDecoder jwtDecoder;

    @Override
    @SneakyThrows
    public TokenDtoResponse auth(LoginDtoRequest request) {

        final var user = userService.findByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new TokenDtoResponse(jwtHelper.generateAccessToken(user), jwtHelper.generateRefreshToken(user));
    }

    @Override
    @SneakyThrows
    public TokenDtoResponse refresh(TokenDtoRequest request) {

        var subject = jwtDecoder.decode(request.refreshToken()).getSubject();

        var user = userService.findByUsername(subject);

        return new TokenDtoResponse(jwtHelper.generateAccessToken(user), jwtHelper.generateRefreshToken(user));
    }

    @Component
    @RequiredArgsConstructor
    static final class JwtHelper {

        private final JwtEncoder jwtEncoder;
        private final TokenProperties tokenProperties;

        public String generateAccessToken(UserDetails user) {

            final var now = Instant.now();

            final var claims = JwtClaimsSet.builder()
                                           .subject(user.getUsername())
                                           .issuer(tokenProperties.issuer())
                                           .issuedAt(now)
                                           .expiresAt(now.plusSeconds(tokenProperties.accessExpirationTime()))
                                           .build();

            return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        }

        public String generateRefreshToken(UserDetails user) {

            final var now = Instant.now();

            final var claims = JwtClaimsSet.builder()
                                           .subject(user.getUsername())
                                           .issuer(tokenProperties.issuer())
                                           .issuedAt(now)
                                           .claim("Type", "Refresh")
                                           .expiresAt(now.plusSeconds(tokenProperties.refreshExpirationTime()))
                                           .build();

            return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        }
    }

}
