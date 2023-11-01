package conecta.vagas.api.domain.company;

import conecta.vagas.api.domain.user.UserDataRegister;
import jakarta.validation.constraints.NotNull;

public class CompanyDataRegister extends UserDataRegister {
    @NotNull(message = "Necessário informar CNPJ")
    public String cnpj;
    public String city;

    public String getCnpj() {
        return cnpj;
    }
    public String getCity() {
        return city;
    }
}

