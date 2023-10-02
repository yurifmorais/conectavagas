package conecta.vagas.api.domain.jobVacancy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobVRepository extends JpaRepository<JobV, Long> {
}
