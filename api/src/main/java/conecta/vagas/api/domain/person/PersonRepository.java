package conecta.vagas.api.domain.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import conecta.vagas.api.domain.person.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    //teste abaixo
    Person findByEmail(String email);
    //teste acima
}

