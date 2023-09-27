package kz.syllabus.config.security;

import kz.syllabus.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    public final UserService userService;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        var user = userService.findByUsername(source.getSubject());
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}
