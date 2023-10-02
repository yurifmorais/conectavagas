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
        @NotNull
        Date endDate,
        @NotBlank
        String internshipType,
        @NotBlank
        String requirements,
        @NotBlank
        String benefits,
        @NotNull
        Long userId) {
}
