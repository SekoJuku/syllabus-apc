package kz.syllabus.entity;


import jakarta.persistence.MappedSuperclass;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
@MappedSuperclass
public class Base {
    private Long id;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    
    public void prePersist() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    public void preUpdate() {
        updatedAt = ZonedDateTime.now();
    }
}
