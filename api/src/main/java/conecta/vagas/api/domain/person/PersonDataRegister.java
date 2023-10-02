package conecta.vagas.api.domain.person;


import conecta.vagas.api.domain.user.UserDataRegister;

public class PersonDataRegister extends UserDataRegister {
    public String cpf;
    public String surname;

    public String getCPF() {
        return cpf;
    }
    public String getSurname() {
        return surname;
    }
}
