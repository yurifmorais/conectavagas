package conecta.vagas.api.domain.jobVacancy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobVDataRegister(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String location,
        Filters filters,
        @NotNull
        Double salary) {
}
