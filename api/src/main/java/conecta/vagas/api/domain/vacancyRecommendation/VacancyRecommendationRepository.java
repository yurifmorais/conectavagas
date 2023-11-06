package conecta.vagas.api.domain.vacancyRecommendation;

import conecta.vagas.api.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VacancyRecommendationRepository extends JpaRepository<VacancyRecommendation, Long> {
    Page<VacancyRecommendation> findByUserAndReadFalse(User user, Pageable pagination);

    @Modifying
    @Query(value = "UPDATE vacancy_recommendations SET recommendation_read = 1 WHERE id IN :recommendations AND user_id = :userId", nativeQuery = true)
    void markAsRead(@Param("userId") Long userId, @Param("recommendations") Long[] recommendations);
}