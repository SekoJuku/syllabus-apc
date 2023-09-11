package kz.syllabus.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("security.encoder")
public record EncoderProperties(
        Integer strength
) {
}
