package conecta.vagas.api.domain.person;


import conecta.vagas.api.domain.user.UserDataRegister;

import java.util.HashSet;
import java.util.Set;

public class PersonDataRegister extends UserDataRegister {
    public String cpf;
    public String surname;
    public String github;
    public String linkedin;
    public Set<Long> tagIds = new HashSet<>();
}
