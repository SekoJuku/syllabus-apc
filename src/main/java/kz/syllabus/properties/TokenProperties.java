package kz.syllabus.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.token")
public record TokenProperties(
        String prefix,
        String clientIp,
        String secret,
        String issuer,
        String audience,
        int accessExpirationTime,
        int refreshExpirationTime,
        String jwtTokenHeader,
        String tokenCannotBeVerified
) {

}
