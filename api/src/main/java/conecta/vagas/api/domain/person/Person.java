package conecta.vagas.api.domain.person;
import conecta.vagas.api.domain.Application.Application;
import conecta.vagas.api.domain.tag.Tag;
import conecta.vagas.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Setter
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    public Person(PersonDataRegister personDataRegister) {
        super(personDataRegister);
        this.cpf = personDataRegister.cpf;
        this.surname = personDataRegister.surname;
    }
}
