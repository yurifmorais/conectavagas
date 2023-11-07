package conecta.vagas.api.controller;

import conecta.vagas.api.domain.vacancyRecommendation.MarkAsReadData;
import conecta.vagas.api.domain.vacancyRecommendation.VacancyRecommendationRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendations")
@SecurityRequirement(name = "bearer-key")
public class VacancyRecommendationsController {
    private VacancyRecommendationRepository recommendationRepository;

    public VacancyRecommendationsController(VacancyRecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @PutMapping("/person/{userId}/read")
    @Transactional
    public ResponseEntity markAsRead(@PathVariable long userId, @RequestBody MarkAsReadData requestBody){
        recommendationRepository.markAsRead(userId, requestBody.recommendations());

        return ResponseEntity.noContent().build();
    }
}
