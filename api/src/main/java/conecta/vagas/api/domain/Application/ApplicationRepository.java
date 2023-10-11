package conecta.vagas.api.domain.Application;

import conecta.vagas.api.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Set<Application> findByPerson(Person person);

    @Query("SELECT a FROM Application a WHERE a.person.ID = :personId")
    Set<Application> findByPersonId(@Param("personId") Long personId);

}
