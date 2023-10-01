package conecta.vagas.api.domain.jobVacancy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record JobVDataRegister(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String location,
        Filters filters,
        @NotNull
        Double salary,
        @NotNull
        Date postDate,
        Date endDate,
        String internshipType,
        String requirements,
        String benefits,
        @NotNull
        Long userId) {
}
