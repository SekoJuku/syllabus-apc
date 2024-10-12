package kz.syllabus.common.persistence.model;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public class Base {

    @Id
    protected Long id;

    @CreationTimestamp
    protected ZonedDateTime createdAt;

    @UpdateTimestamp
    protected ZonedDateTime updatedAt;

}
