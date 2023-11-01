package conecta.vagas.api.domain.jobVacancy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

public record JobVDataRegister(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String location,
        @NotNull
        Double salary,
        @NotNull
        Date postDate,
        Date endDate,
        String internshipType,
        String requirements,
        String benefits,
        @NotNull
        Long userId,
        Set<Long> tagIds) {

        public JobVDataRegister{
            if(tagIds == null)
                tagIds = Set.of();
        }
}
