package conecta.vagas.api.domain.vacancyRecommendation;

import java.util.Date;

public record RecommendationListingData(Long id, RecommendationListingDataJobVacancy jobVacancy, Date createdAt) {
    public RecommendationListingData(VacancyRecommendation recommendation){
        this(recommendation.getId(), new RecommendationListingDataJobVacancy(recommendation.getJobVacancy()), recommendation.getCreatedAt());
    }
}

