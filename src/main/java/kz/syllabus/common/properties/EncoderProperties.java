package kz.syllabus.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("security.encoder")
public record EncoderProperties(
        Integer strength
) {
}
