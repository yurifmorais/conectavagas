package conecta.vagas.api.domain.vacancyRecommendation;

import conecta.vagas.api.domain.jobVacancy.JobV;

public record RecommendationListingDataJobVacancy(long id, String title, String location, String companyName) {
    public RecommendationListingDataJobVacancy(JobV vacancy) {
        this(vacancy.getID(), vacancy.getTitle(), vacancy.getLocation(), vacancy.getUser().getName());
    }
}
