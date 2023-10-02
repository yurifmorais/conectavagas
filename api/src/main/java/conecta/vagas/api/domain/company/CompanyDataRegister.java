package conecta.vagas.api.domain.company;

import conecta.vagas.api.domain.user.UserDataRegister;

public class CompanyDataRegister extends UserDataRegister {
    public String cnpj;
    public String city;

    public String getCnpj() {
        return cnpj;
    }
    public String getCity() {
        return city;
    }
}

