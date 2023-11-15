package conecta.vagas.api.domain.vacancyRecommendation;

import conecta.vagas.api.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRecommendationRepository extends JpaRepository<VacancyRecommendation, Long> {
    Page<VacancyRecommendation> findByUser(User user, Pageable pagination);
}