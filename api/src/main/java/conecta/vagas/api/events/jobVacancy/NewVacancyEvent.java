package conecta.vagas.api.events.jobVacancy;

import lombok.Getter;
import org.jobrunr.jobs.lambdas.JobRequest;

@Getter
public class NewVacancyEvent implements JobRequest {

    private final Long jobVacancyId;

    protected NewVacancyEvent(){
        this(null);
    }

    public NewVacancyEvent(Long jobVacancyId){
        this.jobVacancyId = jobVacancyId;
    }

    @Override
    public Class<JobVacancyEventHandler> getJobRequestHandler() {
        return JobVacancyEventHandler.class;
    }
}
