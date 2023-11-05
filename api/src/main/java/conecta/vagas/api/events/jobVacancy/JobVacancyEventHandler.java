package conecta.vagas.api.events.jobVacancy;

import conecta.vagas.api.domain.jobVacancy.JobV;
import conecta.vagas.api.domain.jobVacancy.JobVRepository;
import conecta.vagas.api.domain.tag.Tag;
import conecta.vagas.api.domain.user.User;
import conecta.vagas.api.domain.user.UserRepository;
import conecta.vagas.api.domain.vacancyRecommendation.VacancyRecommendation;
import conecta.vagas.api.domain.vacancyRecommendation.VacancyRecommendationRepository;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public final class JobVacancyEventHandler implements JobRequestHandler<NewVacancyEvent> {
    private static Double MINIMUM_COMPATIBILITY_RATIO = 0.5;

    private final JobVRepository jobVacancyRepository;

    private final UserRepository userRepository;

    private final VacancyRecommendationRepository vacancyRecommendationRepository;

    public JobVacancyEventHandler(JobVRepository jobVacancyRepository, UserRepository userRepository, VacancyRecommendationRepository vacancyRecommendationRepository) {
        this.jobVacancyRepository = jobVacancyRepository;
        this.userRepository = userRepository;
        this.vacancyRecommendationRepository = vacancyRecommendationRepository;
    }

    @Override
    public void run(NewVacancyEvent data) throws Exception {

        var jobVacancySearch = jobVacancyRepository.findById(data.getJobVacancyId());

        if(jobVacancySearch.isEmpty())
            return;

        var vacancy = jobVacancySearch.get();

        var usersToNotify = getUsersToNotify(vacancy);

        for(var user : usersToNotify)
            createJobVacancyCompatibleNotification(vacancy, user);
    }

    private Collection<User> getUsersToNotify(JobV vacancy){
        var tagIds = vacancy.getTags().stream().map(Tag::getID).toList();

        var minimumTags = Math.floor(tagIds.size() * MINIMUM_COMPATIBILITY_RATIO);

        return userRepository.findByTags(tagIds, (int)minimumTags);
    }

    private void createJobVacancyCompatibleNotification(JobV vacancy, User user){
        vacancyRecommendationRepository.save(new VacancyRecommendation(vacancy, user));
    }
}
