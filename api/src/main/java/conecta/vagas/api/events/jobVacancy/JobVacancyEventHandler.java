package conecta.vagas.api.events.jobVacancy;

import conecta.vagas.api.domain.jobVacancy.JobV;
import conecta.vagas.api.domain.jobVacancy.JobVRepository;
import conecta.vagas.api.domain.tag.Tag;
import conecta.vagas.api.domain.user.User;
import conecta.vagas.api.domain.user.UserRepository;
import conecta.vagas.api.domain.userVacancyNotification.UserVacancyNotification;
import conecta.vagas.api.domain.userVacancyNotification.UserVacancyNotificationRepository;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public final class JobVacancyEventHandler implements JobRequestHandler<NotifyNewVacancyToCompatibleUsersEvent> {
    private static Double MINIMUM_COMPATIBILITY_RATIO = 0.5;

    private final JobVRepository jobVacancyRepository;
    private final UserRepository userRepository;
    private final UserVacancyNotificationRepository userVacancyNotificationRepository;

    public JobVacancyEventHandler(JobVRepository jobVacancyRepository, UserRepository userRepository, UserVacancyNotificationRepository userVacancyNotificationRepository) {
        this.jobVacancyRepository = jobVacancyRepository;
        this.userRepository = userRepository;
        this.userVacancyNotificationRepository = userVacancyNotificationRepository;
    }

    @Override
    public void run(NotifyNewVacancyToCompatibleUsersEvent data) throws Exception {
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
        userVacancyNotificationRepository.save(new UserVacancyNotification(vacancy, user));
    }
}
