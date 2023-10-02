package conecta.vagas.api.domain.Application;

import conecta.vagas.api.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Set<Application> findByPerson(Person person);
}
