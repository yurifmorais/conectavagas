package conecta.vagas.api.domain.jobVacancy;

import conecta.vagas.api.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobVRepository extends JpaRepository<JobV, Long> {

    Page<JobV> findByUser(User currentUser, Pageable paginacao);
}
