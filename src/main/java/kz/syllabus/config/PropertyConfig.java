package kz.syllabus.config;

import kz.syllabus.config.properties.JwtProperties;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@AllArgsConstructor
public class PropertyConfig {
    @Autowired private Environment environment;

    @Bean
    public JwtProperties jwtProperties() {
        return new JwtProperties(environment.getProperty("jwt.secret"));
    }
}
