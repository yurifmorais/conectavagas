package conecta.vagas.api.domain.jobVacancy;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

public record JobVDataUpdate(
        @NotNull
        Long id,
        String title,
        String description,
        String location,
        Double salary,
        Date postDate,
        Date endDate,
        String internshipType,
        String requirements,
        String benefits,
        Set<Long> tagIds) {
}