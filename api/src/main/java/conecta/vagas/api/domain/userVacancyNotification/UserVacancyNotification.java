package conecta.vagas.api.domain.userVacancyNotification;

import conecta.vagas.api.domain.jobVacancy.JobV;
import conecta.vagas.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_vacancy_notifications")
@EqualsAndHashCode(of = "ID")
@Entity
public class UserVacancyNotification {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_vacancy_id")
    private JobV jobVacancy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserVacancyNotification(JobV vacancy, User user){
        this.jobVacancy = vacancy;
        this.user = user;
    }
}
