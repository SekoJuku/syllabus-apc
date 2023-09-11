package kz.syllabus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("kz.syllabus.properties")
public class SyllabusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyllabusApplication.class, args);
    }

}
