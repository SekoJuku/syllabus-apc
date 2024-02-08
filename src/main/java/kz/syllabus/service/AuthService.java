package kz.syllabus.service;

import kz.syllabus.dto.request.TokenDtoRequest;
import kz.syllabus.dto.request.user.LoginDtoRequest;
import kz.syllabus.dto.response.TokenDtoResponse;
import kz.syllabus.properties.TokenProperties;
import kz.syllabus.service.user.UserService;
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
public class AuthService {

    private final UserService userService;
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;
    private final JwtDecoder jwtDecoder;

    @SneakyThrows
    public TokenDtoResponse auth(LoginDtoRequest request) {
        var user = userService.findByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Invalid password");
        var accessToken = jwtHelper.generateAccessToken(user);
        var refreshToken = jwtHelper.generateRefreshToken(user);
        return new TokenDtoResponse(accessToken, refreshToken);
    }

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

}
