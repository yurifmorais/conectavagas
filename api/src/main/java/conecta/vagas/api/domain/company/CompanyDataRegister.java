package conecta.vagas.api.domain.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CompanyDataRegister(
        @NotBlank
        String cnpj,
        @NotBlank
        String nomeFantasia,
        @NotNull
        Integer situacaoCadastral,
        @NotBlank
        String cnae,
        @NotBlank
        String uf,
        @NotNull
        Integer municipio) {
}
