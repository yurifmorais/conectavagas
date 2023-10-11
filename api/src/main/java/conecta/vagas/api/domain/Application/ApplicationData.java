package conecta.vagas.api.domain.Application;

import jakarta.validation.constraints.NotNull;

public record ApplicationData(@NotNull
                              Long personID,
                              @NotNull
                              Long jobID
) {
}