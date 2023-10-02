package conecta.vagas.api.domain.person;
import conecta.vagas.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("person")
public class Person extends User {

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "surname")
    private String surname;

    public Person(PersonDataRegister personDataRegister) {
        super(personDataRegister);
        this.cpf = personDataRegister.getCPF();
        this.surname = personDataRegister.getSurname();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String newCpf) {
        this.cpf = newCpf;
    }
}