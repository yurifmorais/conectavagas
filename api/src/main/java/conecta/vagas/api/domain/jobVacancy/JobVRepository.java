package conecta.vagas.api.domain.jobVacancy;

import conecta.vagas.api.domain.tag.Tag;
import conecta.vagas.api.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface JobVRepository extends JpaRepository<JobV, Long> {
    Page<JobV> findDistinctByUserAndTagsIn(User currentUser, List<Tag> tags, Pageable paginacao);

    Page<JobV> findDistinctByTagsIn(List<Tag> tags, Pageable paginacao);
}
