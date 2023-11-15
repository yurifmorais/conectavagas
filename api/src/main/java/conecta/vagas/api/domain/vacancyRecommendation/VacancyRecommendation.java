package conecta.vagas.api.domain.vacancyRecommendation;

import conecta.vagas.api.domain.jobVacancy.JobV;
import conecta.vagas.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vacancy_recommendations")
@EqualsAndHashCode(of = "ID")
@Entity
public class VacancyRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_vacancy_id")
    private JobV jobVacancy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Date createdAt;

    public VacancyRecommendation(JobV vacancy, User user){
        this.jobVacancy = vacancy;
        this.user = user;
        this.createdAt = new Date();
    }
}
