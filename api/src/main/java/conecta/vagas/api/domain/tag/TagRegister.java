package conecta.vagas.api.domain.tag;

import jakarta.validation.constraints.NotBlank;

public record TagRegister(@NotBlank String title, Long parentId) {
}
