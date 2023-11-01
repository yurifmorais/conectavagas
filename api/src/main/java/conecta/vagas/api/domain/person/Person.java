package conecta.vagas.api.domain.person;
import conecta.vagas.api.domain.Application.Application;
import conecta.vagas.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @OneToMany(mappedBy = "person")
    private Set<Application> applications;

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

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
