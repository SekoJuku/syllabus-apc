package kz.syllabus.persistence.model.syllabus;

import jakarta.persistence.*;
import kz.syllabus.persistence.model.Base;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "syllabus_params")
public class SyllabusParam extends Base {

    @OneToOne(targetEntity = Syllabus.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "syllabus_id", referencedColumnName = "id")
    private Syllabus syllabus;
    private Boolean isFinal;
    private Boolean isSendable;
    private Boolean isSentToCoordinator;
    private Boolean isApprovedByCoordinator;
    private Boolean isSentToDean;
    private Boolean isApprovedByDean;
    private Boolean isActive;

    public static SyllabusParam newEmptyParam() {
        return SyllabusParam.builder()
                .isSendable(false)
                .isSentToCoordinator(false)
                .isApprovedByCoordinator(false)
                .isSentToDean(false)
                .isApprovedByDean(false)
                .isActive(false)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SyllabusParam that = (SyllabusParam) o;
        return Objects.equals(isFinal, that.isFinal)
                && Objects.equals(isSendable, that.isSendable)
                && Objects.equals(isSentToCoordinator, that.isSentToCoordinator)
                && Objects.equals(isApprovedByCoordinator, that.isApprovedByCoordinator)
                && Objects.equals(isSentToDean, that.isSentToDean)
                && Objects.equals(isApprovedByDean, that.isApprovedByDean)
                && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                isFinal,
                isSendable,
                isSentToCoordinator,
                isApprovedByCoordinator,
                isSentToDean,
                isApprovedByDean,
                isActive);
    }
}
