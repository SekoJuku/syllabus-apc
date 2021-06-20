package kz.syllabus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "syllabus_params")
public class SyllabusParam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer syllabusId;
    private Boolean isFinal;
    private Boolean isSendable;
    private Boolean isSentToCoordinator;
    private Boolean isApprovedByCoordinator;
    private Boolean isSentToDean;
    private Boolean isApprovedByDean;
    private Boolean isActive;
}
