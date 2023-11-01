package conecta.vagas.api.events.jobVacancy;

import lombok.Getter;
import org.jobrunr.jobs.lambdas.JobRequest;

@Getter
public class NotifyNewVacancyToCompatibleUsersEvent implements JobRequest {

    private final Long jobVacancyId;

    public NotifyNewVacancyToCompatibleUsersEvent(Long jobVacancyId){
        this.jobVacancyId = jobVacancyId;
    }

    @Override
    public Class<JobVacancyEventHandler> getJobRequestHandler() {
        return JobVacancyEventHandler.class;
    }
}
