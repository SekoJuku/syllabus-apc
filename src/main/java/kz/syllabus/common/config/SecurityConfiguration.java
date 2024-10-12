package kz.syllabus.common.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import kz.syllabus.common.constants.SecurityConstants.Roles;
import kz.syllabus.common.properties.EncoderProperties;
import kz.syllabus.common.properties.RsaProperties;
import kz.syllabus.common.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserService userService;
    private final RsaProperties rsaProperties;
    private final EncoderProperties encoderProperties;

    @Bean
    public AuthenticationManager authenticationManager() {
        var authenticationProvider = new DaoAuthenticationProvider(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic(AbstractHttpConfigurer::disable)
                   .csrf(CsrfConfigurer::disable)
                   .sessionManagement(
                           config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                   .authorizeHttpRequests(
                           auth ->
                                   auth.requestMatchers("/dean/**")
                                       .hasRole(Roles.DEAN)
                                       .requestMatchers("/coordinator/**")
                                       .hasRole(Roles.COORDINATOR)
                                       .requestMatchers("/teacher/**")
                                       .hasRole(Roles.TEACHER)
                                       .requestMatchers("/student/**")
                                       .hasRole(Roles.STUDENT)
                                       .requestMatchers("/instructor/**")
                                       .permitAll()
                                       .requestMatchers("/auth/**")
                                       .permitAll()
                                       .requestMatchers("/test/**")
                                       .permitAll()
                                       .anyRequest()
                                       .authenticated())
                   .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
                   .build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        var jwk = new RSAKey.Builder(rsaProperties.publicKey())
                .privateKey(rsaProperties.privateKey())
                .build();
        var jwtSource = new ImmutableJWKSet<>(new JWKSet(jwk));

        return new NimbusJwtEncoder(jwtSource);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaProperties.publicKey()).build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET", "DELETE", "OPTIONS", "PUT")
                        .allowedHeaders("*")
                        .exposedHeaders("*");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(encoderProperties.strength());
    }
}
